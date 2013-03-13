package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common;

import java.util.Properties;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 6, 2013
 * 
 */

public abstract class AbstractMetric {

	private int cyclo;
	private String url;

	public int getCyclo() {
		return cyclo;
	}

	public void setCyclo(int cyclo) {
		this.cyclo = cyclo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Method that takes a Properties object and loads settings for a particular metric.
	 * @param properties
	 */
	public abstract void loadMetricProperties(Properties properties);
	
}
