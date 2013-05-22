package main.java.br.com.arida.ufc.mydbaasframework.api.client;

import java.util.List;

/**
 * Class that controls access to resource metrics.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */
public abstract class MyDBaaSMetrics {

	private MyDBaaSClient client;
	
	/**
	 * Default constructor
	 * @param client - MyDBaaSClient object
	 */
	public MyDBaaSMetrics(MyDBaaSClient client) {
		this.client = client;
	}
	
	public MyDBaaSClient getClient() {
		return client;
	}

	public void setClient(MyDBaaSClient client) {
		this.client = client;
	}
	
	public abstract Object getMetricLastCollection(String metricName, int resourceID, String resourceType);
	
	public abstract List<Object> getMetricAllCollection(String metricName, int resourceID, String resourceType);
	
	public abstract List<Object> getMetricCollectionBetween(String metricName, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract List<Object> getMetricMaxBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract List<Object> getMetricMinBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract Object getMetricAverageBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
}
