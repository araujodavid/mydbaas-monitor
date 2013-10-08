package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.StatementDMLMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import java.sql.Connection;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 10, 2013
 */
public class StatementDMLCollector extends AbstractCollector<StatementDMLMetric> {

	private boolean firstCycle;
	private int inserts;
	private int updates;
	private int selects;
	private int deletes;
	
	public StatementDMLCollector(int identifier, String type) {
		super(identifier, type);
		this.firstCycle = true;
		this.inserts = 0;
		this.updates = 0;
		this.selects = 0;
		this.deletes = 0;
	}

	@Override
	public void loadMetric(Object[] args) throws NumberFormatException, ClassNotFoundException, SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(Integer.valueOf((String) args[0]), null);
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			resultSet = databaseConnection.executeSQL(connection, "show global status where variable_name in ('Com_insert', 'Com_select', 'Com_update', 'Com_delete');", null);
		}
		
		//Dealing with the return of the query and inserting the data in the object of the metric
		//If first cycle: the metric has zero values ​​and the monitored value is stored
		if (firstCycle == true) {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_insert":
					inserts = resultSet.getInt("Value");
					break;
				case "Com_select":
					selects = resultSet.getInt("Value");
					break;
				case "Com_update":
					updates = resultSet.getInt("Value");
					break;
				case "Com_delete":
					deletes = resultSet.getInt("Value");
					break;
				}
	        }
			this.metric.setStatementDMLInserts(0);
			this.metric.setStatementDMLUpdates(0);
			this.metric.setStatementDMLSelects(0);
			this.metric.setStatementDMLDeletes(0);
		//Otherwise: the new value is subtracted by the value stored and the result is set in the metric
		} else {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_insert":
					this.metric.setStatementDMLInserts(resultSet.getInt("Value") - inserts);
					inserts = resultSet.getInt("Value");
					break;
				case "Com_select":
					this.metric.setStatementDMLSelects(resultSet.getInt("Value") - selects);
					selects = resultSet.getInt("Value");
					break;
				case "Com_update":
					this.metric.setStatementDMLUpdates(resultSet.getInt("Value") - updates);
					updates = resultSet.getInt("Value");
					break;
				case "Com_delete":
					this.metric.setStatementDMLDeletes(resultSet.getInt("Value") - deletes);
					deletes = resultSet.getInt("Value");
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
		this.metric = StatementDMLMetric.getInstance();
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
					System.out.println("Problem loading the StatementDML metric value (DBMS)");
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
						System.out.println("StatementDML request error!");
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
