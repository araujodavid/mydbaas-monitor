package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common;

import java.util.Properties;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public interface LoadMetric {

	/**
	 * Method that takes a Properties object and loads settings for a particular metric.
	 * @param properties
	 */
	public abstract void loadMetricProperties(Properties properties);
}
