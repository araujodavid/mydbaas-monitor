package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 11, 2013
 */
public class WorkloadStatus extends AbstractDatabaseMetric {
	
	private String workloadStatusQuery;
	private long workloadStatusSelectivity;
	private double workloadStatusResponseTime;
	private double workloadStatusThroughput;
	
	public String getWorkloadStatusQuery() {
		return workloadStatusQuery;
	}

	public void setWorkloadStatusQuery(String workloadStatusQuery) {
		this.workloadStatusQuery = workloadStatusQuery;
	}

	public long getWorkloadStatusSelectivity() {
		return workloadStatusSelectivity;
	}

	public void setWorkloadStatusSelectivity(long workloadStatusSelectivity) {
		this.workloadStatusSelectivity = workloadStatusSelectivity;
	}

	public double getWorkloadStatusResponseTime() {
		return workloadStatusResponseTime;
	}

	public void setWorkloadStatusResponseTime(double workloadStatusResponseTime) {
		this.workloadStatusResponseTime = workloadStatusResponseTime;
	}

	public double getWorkloadStatusThroughput() {
		return workloadStatusThroughput;
	}

	public void setWorkloadStatusThroughput(double workloadStatusThroughput) {
		this.workloadStatusThroughput = workloadStatusThroughput;
	}

	@Override
	public String toString() {
		return "database";
	}
	
	@Override
	public List<WorkloadStatus> jsonToList(String json) {
		Gson gson = new Gson();
		List<WorkloadStatus> workloadStatusList = gson.fromJson(json, new TypeToken<List<WorkloadStatus>>(){}.getType());
		return workloadStatusList;
	}
}
