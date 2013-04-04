package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.util.TypeTranslater;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 2.0
 * @since April 1, 2013 
 */

@Component
public class MetricRepository {
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

	/**
	 * Method to save a metric in the database
	 * @param metric - given metric object
	 * @param machine
	 * @param recordDate
	 * @return true if the metric is saved
	 */
	public boolean saveMetric(Object metric, int machine, String recordDate) {
		List<Field> fields = this.getMetricFields(metric);
		Class<?> clazz = metric.getClass();	
		return true;
	}//saveMetric()
	
	/**
	 * Method to create a table in the database for a given metric
	 * @param metric - given metric object
	 * @return true if the table is created
	 */
	public boolean createMetricTable(Object metric) {
		List<Field> fields = this.getMetricFields(metric);
		String tableSQL = this.makeCreateTableSQL(metric, fields);
		return true;
	}//createMetricTable()
	
	/**
	 * Method to recover the fields of a given metric
	 * @param metric - given metric object
	 * @return the list of class fields
	 */
	public List<Field> getMetricFields(Object metric) {
		List<Field> fields = new ArrayList<Field>();		
		//Gets the class of the metric
		Class<?> clazz = metric.getClass();		
		//Gets fields from the class
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		return fields;
	}//getMetricFields()
	
	/**
	 * Method for mounting SQL table creation
	 * @param metricName
	 * @param fields
	 * @return a SQL ready
	 */
	public String makeCreateTableSQL(Object metric, List<Field> fields) {
		String clazzName = metric.getClass().getSimpleName().toLowerCase();
		StringBuilder sql = new StringBuilder();
		sql.append("create table `"+clazzName+"_metric` (\n")
		   .append("`id` int(11) NOT NULL AUTO_INCREMENT,\n")
		   .append("`record_date` datetime NOT NULL,\n");
		
		for (Field field : fields) {
			sql.append("`"+field.getName().toLowerCase()+"` "+TypeTranslater.getSQLType(field.getType())+" DEFAULT NULL,\n");
		}
		
		sql.append("`identifier` int(11) NOT NULL,\n")
		   .append("PRIMARY KEY (`id`),\n")
		   .append("KEY `fk_"+clazzName+"_metric_idx` (`identifier`),\n");
		
		if (metric.toString().equals("machine")) {			
			sql.append("CONSTRAINT `fk_"+clazzName+"_metric_machine` FOREIGN KEY (`identifier`) REFERENCES `virtual_machine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		} else {
			sql.append("CONSTRAINT `fk_"+clazzName+"_metric_database` FOREIGN KEY (`identifier`) REFERENCES `database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		}
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8");
		return sql.toString();
	}
}
