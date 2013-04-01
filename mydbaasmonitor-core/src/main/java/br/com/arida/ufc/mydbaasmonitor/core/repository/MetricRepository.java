package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 1.0
 * @since April 1, 2013 
 */

@Component
public class MetricRepository {

	/**
	 * Method to save a metric in the database
	 * @param metric - given metric
	 * @param machine
	 * @param recordDate
	 * @return true if the metric is saved
	 */
	public boolean saveMetric(Object metric, int machine, String recordDate) {
		List<Field> fields = new ArrayList<Field>();
		
		//Gets the class of the metric
		Class<?> clazz = metric.getClass();
		return true;
	}//saveMetric()
	
	/**
	 * Method to create a table in the database for a given metric
	 * @param metric - given metric
	 * @return true if the table is created
	 */
	public boolean createMetricTable(Object metric) {
		return true;
	}
	
	
	public List<Field> getMetricFields(Object metric) {		
		return null;
	}
}
