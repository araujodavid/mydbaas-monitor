package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.StatementDCLMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 10, 2013
 */
public class StatementDCLCollector extends AbstractCollector<StatementDCLMetric> {

	private boolean firstCycle;
	private int grants;
	private int revokes;
	
	public StatementDCLCollector(int identifier, String type) {
		super(identifier, type);
		this.firstCycle = true;
		this.grants = 0;
		this.revokes = 0;
	}

	@Override
	public void loadMetric(Object[] args) throws SQLException, NumberFormatException, ClassNotFoundException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(Integer.valueOf((String) args[0]), null);
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			resultSet = databaseConnection.executeSQL(connection, "show global status where variable_name in ('Com_grant', 'Com_revoke');", null);
		}
		
		//Dealing with the return of the query and inserting the data in the object of the metric
		//If first cycle: the metric has zero values ​​and the monitored value is stored
		if (firstCycle == true) {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_grant":
					grants = resultSet.getInt("Value");
					break;
				case "Com_revoke":
					revokes = resultSet.getInt("Value");
					break;
				}
	        }
			this.metric.setStatementDCLGrant(0);
			this.metric.setStatementDCLRevoke(0);
		//Otherwise: the new value is subtracted by the value stored and the result is set in the metric
		} else {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_commit":
					this.metric.setStatementDCLGrant(resultSet.getInt("Value") - grants);
					grants = resultSet.getInt("Value");
					break;
				case "Com_rollback":
					this.metric.setStatementDCLRevoke(resultSet.getInt("Value") - revokes);
					revokes = resultSet.getInt("Value");
					break;
				}
	        }
		}
		//Close the connection and resultset
		resultSet.close();
		connection.close();
	}

	@Override
	public void run() {
		this.metric = StatementDCLMetric.getInstance();
		HttpResponse response;
		List<NameValuePair> params = null;
		
		if (this.metric.getDBMSs().length > 0) {
			for (String dbms : this.metric.getDBMSs()) {
				//Load the metric
				try {
					this.loadMetric(new Object[] {dbms});
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Problem loading the StatementDCL metric value (DBMS)");
					e.printStackTrace();
				}
				
				//Creates request parameters
				try {
					params = this.loadRequestParams(new Date(), Integer.parseInt(dbms), 0);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				
				//Sends the collected metric
				try {
					response = this.sendMetric(params);
					System.out.println(response.getStatusLine());
					if (response.getStatusLine().getStatusCode() != 202) {
						System.out.println("StatementDCL request error!");
						EntityUtils.consume(response.getEntity());
					}
					EntityUtils.consume(response.getEntity());
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//After the first cycle the flag is changed to false
		this.firstCycle = false;
	}
}
