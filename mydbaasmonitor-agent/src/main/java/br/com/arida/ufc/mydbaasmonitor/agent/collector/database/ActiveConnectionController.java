package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.ActiveConnectionMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 17, 2013
 * 
 */
public class ActiveConnectionController extends AbstractCollector<ActiveConnectionMetric> {

	public ActiveConnectionController(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws ClassNotFoundException, SQLException {
		this.metric = ActiveConnectionMetric.getInstance();
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Connection connection = null;
		ResultSet resultSet = null;
		Object[] params;
		switch (String.valueOf(args[0])) {
		case "dbms":
			params = databaseConnection.getConnection(Integer.valueOf((String) args[1]), null);
			connection = (Connection) params[1];
			//Checking database type to make the SQL
			if (params[0].equals("MySQL")) {
				resultSet = databaseConnection.executeSQL(connection, "select count(*) as connections from information_schema.processlist;", null);
			} else if (params[0].equals("PostgreSQL")) {
				resultSet = databaseConnection.executeSQL(connection, "select count(*) as connections from pg_stat_activity;", null);
			}
			while (resultSet != null && resultSet.next()) {
				this.metric.setActiveConnectionAmount(resultSet.getInt("connections"));
            }
			resultSet.close();
			connection.close();
			break;
		case "database":
			params = databaseConnection.getConnection(null, Integer.valueOf((String) args[1]));
			connection = (Connection) params[1];
			//Checking database type to make the SQL
			if (params[0].equals("MySQL")) {
				resultSet = databaseConnection.executeSQL(connection, "select count(*) as connections from information_schema.processlist where db = database();", null);
			} else if (params[0].equals("PostgreSQL")) {
				resultSet = databaseConnection.executeSQL(connection, "select count(*) as connections from pg_stat_activity where datname = '"+params[2]+"';", null);
			}
			while (resultSet != null && resultSet.next()) {
				this.metric.setActiveConnectionAmount(resultSet.getInt("connections"));
            }
			resultSet.close();
			connection.close();
			break;
		}
	}

	@Override
	public void run() {
		this.metric = ActiveConnectionMetric.getInstance();
		HttpResponse response;
		List<NameValuePair> params = null;
		Object[] args = new Object[2];
		//Checking the DBMSs enabled for collection
		if (this.metric.getDBMSs().length > 0) {
			for (String dbms : this.metric.getDBMSs()) {
				args[0] = "dbms";
				args[1] = dbms;
				try {
					this.loadMetric(args);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Problem loading the ActiveConnections metric value (DBMS)");
					e.printStackTrace();
				}
				
				try {
					params = this.loadRequestParams(new Date(), Integer.parseInt(dbms), 0);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
					System.out.println(response.getStatusLine());
					if (response.getStatusLine().getStatusCode() != 202) {
						System.out.println("Active connections request error!");
						EntityUtils.consume(response.getEntity());
					}
					EntityUtils.consume(response.getEntity());
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//Checking the Databases enabled for collection
		if (this.metric.getDatabases().length > 0) {
			for (String database : this.metric.getDatabases()) {
				args[0] = "database";
				args[1] = database;
				try {
					this.loadMetric(args);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Problem loading the ActiveConnections metric value (DBMS)");
					e.printStackTrace();
				}
				
				try {
					params = this.loadRequestParams(new Date(), 0, Integer.parseInt(database));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
					System.out.println(response.getStatusLine());
					if (response.getStatusLine().getStatusCode() != 202) {
						System.out.println("Active connections request error!");
						EntityUtils.consume(response.getEntity());
					}
					EntityUtils.consume(response.getEntity());
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
