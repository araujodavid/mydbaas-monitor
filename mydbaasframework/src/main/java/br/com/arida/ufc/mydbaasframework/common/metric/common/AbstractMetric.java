package main.java.br.com.arida.ufc.mydbaasframework.common.metric.common;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 21, 2013
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
	
	@Override
	public abstract String toString();
	
}
