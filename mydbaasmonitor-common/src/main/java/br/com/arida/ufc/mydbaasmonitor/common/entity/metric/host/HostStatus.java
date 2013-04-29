package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 28, 2013
 * 
 */
public class HostStatus extends AbstractMetric {

	private double hostStatusFreeMemory;
	private int hostStatusInactiveDomains;
	private int hostStatusActiveDomains;
	
	public double getHostStatusFreeMemory() {
		return hostStatusFreeMemory;
	}

	public void setHostStatusFreeMemory(double hostStatusFreeMemory) {
		this.hostStatusFreeMemory = hostStatusFreeMemory;
	}

	public int getHostStatusInactiveDomains() {
		return hostStatusInactiveDomains;
	}

	public void setHostStatusInactiveDomains(int hostStatusInactiveDomains) {
		this.hostStatusInactiveDomains = hostStatusInactiveDomains;
	}

	public int getHostStatusActiveDomains() {
		return hostStatusActiveDomains;
	}

	public void setHostStatusActiveDomains(int hostStatusActiveDomains) {
		this.hostStatusActiveDomains = hostStatusActiveDomains;
	}

	@Override
	public String toString() {
		return "host";
	}

}
