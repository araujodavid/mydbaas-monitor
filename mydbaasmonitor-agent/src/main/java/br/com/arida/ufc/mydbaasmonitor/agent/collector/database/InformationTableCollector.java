package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.InformationTableMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.InformationTable;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 11, 2013
 */
public class InformationTableCollector extends AbstractCollector<InformationTableMetric> {

	private List<InformationTable> informationTableMetrics;
	
	public InformationTableCollector(int identifier, String type) {
		super(identifier, type);
		this.informationTableMetrics = new ArrayList<InformationTable>();
	}

	@Override
	public void loadMetric(Object[] args) throws NumberFormatException, ClassNotFoundException, SQLException  {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(null, Integer.valueOf((String) args[0]));
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			String sql = "select concat(table_schema, '.', table_name) 'table_name', table_rows, " +
						 "round(`avg_row_length` / 1024 , 2) row_average, " +
						 "round(data_length / (1024*1024) , 2) data_length,  " +
						 "round(index_length / (1024*1024) , 2) index_length, " +
						 "round(round(data_length + index_length) / (1024*1024) , 2) total_size " +
						 "from information_schema.tables where table_schema = database() " +
					     "order by data_length desc;";
			resultSet = databaseConnection.executeSQL(connection, sql, null);
		}
		//Dealing with the return of the query and inserting the data in the object of the metric
		while (resultSet != null && resultSet.next()) {
			InformationTable informationTable = new InformationTable();
			informationTable.setInformationTableName(resultSet.getString("table_name"));
			informationTable.setInformationTableAmountRows(resultSet.getLong("table_rows"));
			informationTable.setInformationTableRowAverage(resultSet.getDouble("row_average"));
			informationTable.setInformationTableDataLength(resultSet.getDouble("data_length"));
			informationTable.setInformationTableIndexLength(resultSet.getDouble("index_length"));
			informationTable.setInformationTableTotalSize(resultSet.getDouble("total_size"));
			this.informationTableMetrics.add(informationTable);
        }
		resultSet.close();
		connection.close();
	}

	@Override
	public void run() {
		this.metric = InformationTableMetric.getInstance();
		HttpResponse response;
		List<NameValuePair> params = null;
		
		if (this.metric.getDatabases().length > 0) {
			for (String database : this.metric.getDatabases()) {
				//Load the metric
				try {
					this.loadMetric(new Object[] {database});
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("Problem loading the InformationTable metric value (DBMS)");
					e.printStackTrace();
				}
				
				//Creates request parameters
				try {
					params = this.loadRequestParams(new Date(), this.informationTableMetrics, 0, Integer.parseInt(database));
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
						System.out.println("InformationTable request error!");
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
		
		//Release any native resources associated with this sigar instance
		this.informationTableMetrics.clear();
	}
}
