package main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.collector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.WorkloadStatus;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.client.MyDriverClient;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;


public class WorkloadStatusCollector extends AbstractWorkloadCollector<WorkloadStatus> {

	public WorkloadStatusCollector(MyDriverClient client, String url) {
		super(client, url);
	}

	@Override
	public Object[] executeQuery(Database database, String workload) throws ClassNotFoundException, SQLException, Exception {
		WorkloadStatus workloadStatus = new WorkloadStatus();
		//Creates the connection to the instance database
		Connection connection = this.myDBaaSConnection.getConnection(database);
		Statement statement = connection.createStatement();		
		ResultSet resultSet = null;		
		long startTime = System.currentTimeMillis();
		resultSet = statement.executeQuery(workload);
		resultSet.last();
		//Collects the selectivity of the workload - ResultSet size
		long selectivity = resultSet.getRow();
		resultSet.first();
		//Calculate the total response time
		double estimatedTime = System.currentTimeMillis() - startTime;
		workloadStatus.setWorkloadStatusQuery(workload);
		workloadStatus.setWorkloadStatusSelectivity(selectivity);
		//Collects the throughput of workload
		workloadStatus.setWorkloadStatusThroughput(Math.round(selectivity/estimatedTime*100.0)/100.0);
		workloadStatus.setWorkloadStatusResponseTime(estimatedTime);
		
		List<NameValuePair> params = this.loadRequestParams(workloadStatus, new Date(), database);
		HttpResponse response = this.sendMetric(params);
		System.out.println(response.getStatusLine());
		if (response.getStatusLine().getStatusCode() != 202) {
			System.out.println("WorkloadStatus request error!");
			EntityUtils.consume(response.getEntity());
		}
		EntityUtils.consume(response.getEntity());
		return new Object[]{connection, statement, resultSet, workloadStatus};
	}

	@Override
	public Object[] executeUpdate(Database database, String workload) throws ClassNotFoundException, SQLException, Exception {
		WorkloadStatus workloadStatus = new WorkloadStatus();
		//Creates the connection to the instance database
		Connection connection = this.myDBaaSConnection.getConnection(database);
		Statement statement = connection.createStatement();	
		long startTime = System.currentTimeMillis();
		//Collects the selectivity of the workload
		long selectivity = statement.executeUpdate(workload);
		//Calculate the total response time
		double estimatedTime = System.currentTimeMillis() - startTime;
		workloadStatus.setWorkloadStatusQuery(workload);
		workloadStatus.setWorkloadStatusSelectivity(selectivity);
		workloadStatus.setWorkloadStatusResponseTime(estimatedTime);
		if (selectivity > 0) {
			//Collects the throughput of workload
			workloadStatus.setWorkloadStatusThroughput(Math.round(selectivity/estimatedTime*100.0)/100.0);
		}
		
		List<NameValuePair> params = this.loadRequestParams(workloadStatus, new Date(), database);
		HttpResponse response = this.sendMetric(params);
		System.out.println(response.getStatusLine());
		if (response.getStatusLine().getStatusCode() != 202) {
			System.out.println("WorkloadStatus request error!");
			EntityUtils.consume(response.getEntity());
		}
		EntityUtils.consume(response.getEntity());
		return new Object[]{connection, statement, workloadStatus};
	}
}
