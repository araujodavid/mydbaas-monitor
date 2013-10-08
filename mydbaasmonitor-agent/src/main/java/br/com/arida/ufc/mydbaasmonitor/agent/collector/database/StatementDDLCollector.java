package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.StatementDDLMetric;
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
public class StatementDDLCollector extends AbstractCollector<StatementDDLMetric> {

	private boolean firstCycle;
	private int creates;
	private int alters;
	private int drops;
	private int truncates;
	private int renames;
	
	public StatementDDLCollector(int identifier, String type) {
		super(identifier, type);
		this.firstCycle = true;
		this.creates = 0;
		this.alters = 0;
		this.drops = 0;
		this.truncates = 0;
		this.renames = 0;
	}

	@Override
	public void loadMetric(Object[] args) throws NumberFormatException, ClassNotFoundException, SQLException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(Integer.valueOf((String) args[0]), null);
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			resultSet = databaseConnection.executeSQL(connection, "show global status where variable_name in ('Com_create_table', 'Com_alter_table', 'Com_drop_table', 'Com_truncate', 'Com_rename_table');", null);
		}
		
		//Dealing with the return of the query and inserting the data in the object of the metric
		//If first cycle: the metric has zero values ​​and the monitored value is stored
		if (firstCycle == true) {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_create_table":
					creates = resultSet.getInt("Value");
					break;
				case "Com_alter_table":
					alters = resultSet.getInt("Value");
					break;
				case "Com_drop_table":
					drops = resultSet.getInt("Value");
					break;
				case "Com_truncate":
					truncates = resultSet.getInt("Value");
					break;
				case "Com_rename_table":
					renames = resultSet.getInt("Value");
					break;
				}
	        }
			this.metric.setStatementDDLCreate(0);
			this.metric.setStatementDDLAlter(0);
			this.metric.setStatementDDLDrop(0);
			this.metric.setStatementDDLTruncate(0);
			this.metric.setStatementDDLRename(0);
		//Otherwise: the new value is subtracted by the value stored and the result is set in the metric
		} else {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Com_create_table":
					this.metric.setStatementDDLCreate(resultSet.getInt("Value") - creates);
					creates = resultSet.getInt("Value");
					break;
				case "Com_alter_table":
					this.metric.setStatementDDLAlter(resultSet.getInt("Value") - alters);
					alters = resultSet.getInt("Value");
					break;
				case "Com_drop_table":
					this.metric.setStatementDDLDrop(resultSet.getInt("Value") - drops);
					drops = resultSet.getInt("Value");
					break;
				case "Com_truncate":
					this.metric.setStatementDDLTruncate(resultSet.getInt("Value") - truncates);
					truncates = resultSet.getInt("Value");
					break;
				case "Com_rename_table":
					this.metric.setStatementDDLRename(resultSet.getInt("Value") - renames);
					renames = resultSet.getInt("Value");
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
		this.metric = StatementDDLMetric.getInstance();
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
					System.out.println("Problem loading the StatementDDL metric value (DBMS)");
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
						System.out.println("StatementDDL request error!");
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
