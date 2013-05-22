package main.java.br.com.arida.ufc.mydbaasframework.agent.entity;

import java.util.Properties;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 21, 2013
 * 
 */

public interface LoadMetric {

	/**
	 * Method that takes a Properties object and loads settings for a particular metric.
	 * @param properties
	 */
	public abstract void loadMetricProperties(Properties properties);
}
