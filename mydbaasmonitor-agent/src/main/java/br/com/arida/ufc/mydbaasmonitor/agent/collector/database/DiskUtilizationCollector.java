package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.DiskUtilizationMetric;
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
public class DiskUtilizationCollector extends AbstractCollector<DiskUtilizationMetric> {

	private boolean firstCycle;
	private long physicalReads;
	private long logicalReads;
	private long pendingReads;
	private long pendingWrites;
	private long dataRead;
	private long dataWritten;
	private long pagesRead;
	private long pagesWritten;
	private long keyRead;
	private long keyWrites;
	
	public DiskUtilizationCollector(int identifier, String type) {
		super(identifier, type);
		this.firstCycle = true;
	}

	@Override
	public void loadMetric(Object[] args) throws SQLException, NumberFormatException, ClassNotFoundException {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Object[] params = databaseConnection.getConnection(Integer.valueOf((String) args[0]), null);
		Connection connection = (Connection) params[1];
		ResultSet resultSet = null;
		
		//Checking DBMS type to make and execute the SQL
		if (params[0].equals("MySQL")) {
			String sql = "show global status where variable_name in ('Innodb_buffer_pool_reads', 'Innodb_buffer_pool_read_requests', " +
					     "'Innodb_data_pending_reads', 'Innodb_data_pending_writes', 'Innodb_data_read', 'Innodb_data_written', 'Innodb_pages_read', " +
					     "'Innodb_pages_written', 'Key_reads', 'Key_writes');";
			resultSet = databaseConnection.executeSQL(connection, sql, null);
		}
		
		//Dealing with the return of the query and inserting the data in the object of the metric
		//If first cycle: the metric has zero values ​​and the monitored value is stored
		if (firstCycle == true) {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Innodb_buffer_pool_reads":
					physicalReads = resultSet.getLong("Value");
					break;
				case "Innodb_buffer_pool_read_requests":
					logicalReads = resultSet.getLong("Value");
					break;
				case "Innodb_data_pending_reads":
					pendingReads = resultSet.getLong("Value");
					break;
				case "Innodb_data_pending_writes":
					pendingWrites = resultSet.getLong("Value");
					break;
				case "Innodb_data_read":
					dataRead = resultSet.getLong("Value");
					break;
				case "Innodb_data_written":
					dataWritten = resultSet.getLong("Value");
					break;					
				case "Innodb_pages_read":
					pagesRead = resultSet.getLong("Value");
					break;
				case "Innodb_pages_written":
					pagesWritten = resultSet.getLong("Value");
					break;
				case "Key_reads":
					keyRead = resultSet.getLong("Value");
					break;
				case "Key_writes":
					keyWrites = resultSet.getLong("Value");
					break;
				}
	        }
			this.metric.setDiskUtilizationPhysicalReads(0);
			this.metric.setDiskUtilizationLogicalReads(0);
			this.metric.setDiskUtilizationPendingReads(0);
			this.metric.setDiskUtilizationPendingWrites(0);
			this.metric.setDiskUtilizationDataRead(0);
			this.metric.setDiskUtilizationDataWritten(0);
			this.metric.setDiskUtilizationPagesRead(0);
			this.metric.setDiskUtilizationPagesWritten(0);
			this.metric.setDiskUtilizationKeyRead(0);
			this.metric.setDiskUtilizationKeyWrites(0);
		//Otherwise: the new value is subtracted by the value stored and the result is set in the metric
		} else {
			while (resultSet != null && resultSet.next()) {
				switch (resultSet.getString("Variable_name")) {
				case "Innodb_buffer_pool_reads":
					this.metric.setDiskUtilizationPhysicalReads(resultSet.getLong("Value") - physicalReads);
					physicalReads = resultSet.getLong("Value");
					break;
				case "Innodb_buffer_pool_read_requests":
					this.metric.setDiskUtilizationLogicalReads(resultSet.getLong("Value") - logicalReads);
					logicalReads = resultSet.getLong("Value");
					break;
				case "Innodb_data_pending_reads":
					this.metric.setDiskUtilizationPendingReads(resultSet.getLong("Value") - pendingReads);
					pendingReads = resultSet.getLong("Value");
					break;
				case "Innodb_data_pending_writes":
					this.metric.setDiskUtilizationPendingWrites(resultSet.getLong("Value") - pendingWrites);
					pendingWrites = resultSet.getLong("Value");
					break;
				case "Innodb_data_read":
					this.metric.setDiskUtilizationDataRead(resultSet.getLong("Value") - dataRead);
					dataRead = resultSet.getLong("Value");
					break;
				case "Innodb_data_written":
					this.metric.setDiskUtilizationDataWritten(resultSet.getLong("Value") - dataWritten);
					dataWritten = resultSet.getLong("Value");
					break;
				case "Innodb_pages_read":
					this.metric.setDiskUtilizationPagesRead(resultSet.getLong("Value") - pagesRead);
					pagesRead = resultSet.getLong("Value");
					break;
				case "Innodb_pages_written":
					this.metric.setDiskUtilizationPagesWritten(resultSet.getLong("Value") - pagesWritten);
					pagesWritten = resultSet.getLong("Value");
					break;
				case "Key_reads":
					this.metric.setDiskUtilizationKeyRead(resultSet.getLong("Value") - keyRead);
					keyRead = resultSet.getLong("Value");
					break;
				case "Key_writes":
					this.metric.setDiskUtilizationKeyWrites(resultSet.getLong("Value") - keyWrites);
					keyWrites = resultSet.getLong("Value");
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
		this.metric = DiskUtilizationMetric.getInstance();
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
					System.out.println("Problem loading the DiskUtilization metric value (DBMS)");
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
					System.out.println(response.getStatusLine()+" - DiskUtilization");
					if (response.getStatusLine().getStatusCode() != 202) {
						System.out.println("DiskUtilization request error!");
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
