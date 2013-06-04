package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since June 1, 2013
 */
public class HostDomains extends AbstractMetric {
	
	private int hostDomainsInactive;
	private int hostDomainsActive;
	
	public int getHostDomainsInactive() {
		return hostDomainsInactive;
	}

	public void setHostDomainsInactive(int hostDomainsInactive) {
		this.hostDomainsInactive = hostDomainsInactive;
	}

	public int getHostDomainsActive() {
		return hostDomainsActive;
	}

	public void setHostDomainsActive(int hostDomainsActive) {
		this.hostDomainsActive = hostDomainsActive;
	}

	@Override
	public String toString() {
		return "host";
	}

}
