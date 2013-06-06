package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common;

import java.util.List;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public abstract class AbstractMetric {

	private int cyclo;
	private String url;
	private String recordDate;

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
	
	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public abstract String toString();
	
	public abstract List<?> jsonToList(String json);
	
}
