package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.api;

import java.lang.reflect.InvocationTargetException;

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
	
	@Path("/single/{metricName}/{resourceType}/{resourceID}/{queryType}/{startDatetime}/{endDatetime}")
	public void getMetricSingle(String metricName, String resourceType, int resourceID, int queryType, String startDatetime, String endDatetime) {
		Class<?> metricClass = null;
		String sql = null;
		Object metric = null;

		try {
			if (resourceType.equals("dbms") || resourceType.equals("database")) {
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database."+metricName);			
			} else {
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric."+resourceType+"."+metricName);
			}
			
			sql = this.metricRepository.makeQuerySQL(metricClass, resourceID, resourceType, queryType, startDatetime, endDatetime);
			
			metric = this.metricRepository.queryMetric(sql, metricClass);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		result
		.use(Results.json())
		.from(metric, "metric")
		.serialize();
	}
	
	@Path("/multi")
	public void getMetricMulti(String metricName, int resourceID, String resourceType, int queryType, String startDatetime, String endDatetime) {
		Class<?> metricClass;
		try {
			if (resourceType.equals("dbms") || resourceType.equals("database")) {
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database."+metricName);			
			} else {
				metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric."+resourceType+"."+metricName);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
