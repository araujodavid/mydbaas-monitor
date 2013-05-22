package main.java.br.com.arida.ufc.mydbaasframework.core.controller.api;

import br.com.caelum.vraptor.Result;

/**
 * Abstract class for new MetricController
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class AbstractMetricController {

	protected Result result;
	
	public AbstractMetricController(Result result) {
		this.result = result;
	}
	
	public abstract void metricLastCollection(String metricName, int resourceID, String resourceType);
	
	public abstract void metricAllCollection(String metricName, int resourceID, String resourceType);
	
	public abstract void metricCollectionBetween(String metricName, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract void metricMaxBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract void metricMinBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
	
	public abstract void metricAverageBetween(String metricName, String metricField, int resourceID, String resourceType, String startDatetime, String endDatetime);
}
