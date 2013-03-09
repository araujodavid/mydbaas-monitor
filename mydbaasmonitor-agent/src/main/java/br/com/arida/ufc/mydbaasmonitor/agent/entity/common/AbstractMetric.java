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

	private String cyclo;
	private String url;

	public String getCyclo() {
		return cyclo;
	}

	public void setCyclo(String cyclo) {
		this.cyclo = cyclo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public abstract void loadMetricProperties(Properties properties);
	
}
