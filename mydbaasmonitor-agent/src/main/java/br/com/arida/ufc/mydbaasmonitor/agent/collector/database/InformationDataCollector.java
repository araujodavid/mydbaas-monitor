package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.InformationDataMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 11, 2013
 */
public class InformationDataCollector extends AbstractCollector<InformationDataMetric> {

	public InformationDataCollector(int identifier, String type) {
		super(identifier, type);
	}

	@Override
	public void loadMetric(Object[] args) throws NumberFormatException, ClassNotFoundException, SQLException  {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(Integer.valueOf((String) args[0]), null);
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			String sql = "select (select count(*) from information_schema.schemata where schema_name not in ('mysql','information_schema','performance_schema')) as amount_db, " +
						 "(select count(*) from information_schema.tables where table_schema not in ('mysql','information_schema','performance_schema')) as amount_tables, " +
						 "(select count(*) from information_schema.statistics where table_schema not in ('mysql','information_schema','performance_schema')) as amount_index, " +
						 "(select count(*) from information_schema.triggers where trigger_schema not in ('mysql','information_schema','performance_schema')) as amount_trigger, " +
						 "(select count(*) from information_schema.views where table_schema not in ('mysql','information_schema','performance_schema')) as amount_views, " +
						 "(select count(*) from information_schema.routines where routine_schema not in ('mysql','information_schema','performance_schema')) as amount_routines " +
					     "from dual;";
			resultSet = databaseConnection.executeSQL(connection, sql, null);
		}
		//Dealing with the return of the query and inserting the data in the object of the metric
		while (resultSet != null && resultSet.next()) {
			this.metric.setInformationDataTables(resultSet.getInt("amount_tables"));
			this.metric.setInformationDataDatabases(resultSet.getInt("amount_db"));
			this.metric.setInformationDataIndexs(resultSet.getInt("amount_index"));
			this.metric.setInformationDataTriggers(resultSet.getInt("amount_trigger"));
			this.metric.setInformationDataViews(resultSet.getInt("amount_views"));
			this.metric.setInformationDataRoutines(resultSet.getInt("amount_routines"));
        }
		resultSet.close();
		connection.close();
	}

	@Override
	public void run() {
		this.metric = InformationDataMetric.getInstance();
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
					System.out.println("Problem loading the InformationData metric value (DBMS)");
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
						System.out.println("InformationData request error!");
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
	}
}
