package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.api;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

/**
 * Class that handles metrics requests sent by the API module.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 9, 2013 
 */

@Resource
@Path("/metric")
public class MetricController {

	private Result result;
	private MetricRepository metricRepository;
	
	public MetricController(Result result, MetricRepository metricRepository) {
		this.result = result;
		this.metricRepository = metricRepository;
	}
	
	@Path("/single")
	public void getMetricSingle(String metricName, String resourceType, int metricType, int resourceID, String startDatetime, String endDatetime) {
		Class<?> metricClass = null;
		String sql = null;
		
		try {
			if (resourceType.equals("dbms") || resourceType.equals("database")) {			
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database."+metricName);						
			} else {
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric."+resourceType+"."+metricName);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (metricType == MetricRepository.METRIC_MULTI_TYPE) {
			try {			
				sql = this.metricRepository.makeQuerySQL(metricClass, metricType, resourceID, MetricRepository.LAST_COLLECTION, startDatetime, endDatetime);
				List<Object> metric = this.metricRepository.queryMetrics(sql, metricClass);
				
				result
				.use(Results.json())
				.from(metric, "metric")
				.serialize();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else if (metricType == MetricRepository.METRIC_SINGLE_TYPE) {
			try {			
				sql = this.metricRepository.makeQuerySQL(metricClass, metricType, resourceID, MetricRepository.LAST_COLLECTION, startDatetime, endDatetime);
				Object metric = this.metricRepository.queryMetric(sql, metricClass);
				
				result
				.use(Results.json())
				.from(metric, "metric")
				.serialize();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}			
		}		
	}
	
	@Path("/multi")
	public void getMetricMulti(String metricName, int resourceID, String resourceType, int queryType, String startDatetime, String endDatetime) {
		//TODO		
	}
	
}
