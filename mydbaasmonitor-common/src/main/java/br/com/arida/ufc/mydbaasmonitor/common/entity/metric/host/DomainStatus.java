package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 29, 2013
 */
public class DomainStatus extends AbstractMetric {

	private String domainStatusHostIdentifier;
	private double domainStatusCpuPercent;
	private double domainStatusMemoryPercent;
	
	public String getDomainStatusHostIdentifier() {
		return domainStatusHostIdentifier;
	}

	public void setDomainStatusHostIdentifier(String domainStatusHostIdentifier) {
		this.domainStatusHostIdentifier = domainStatusHostIdentifier;
	}

	public double getDomainStatusCpuPercent() {
		return domainStatusCpuPercent;
	}

	public void setDomainStatusCpuPercent(double domainStatusCpuPercent) {
		this.domainStatusCpuPercent = domainStatusCpuPercent;
	}

	public double getDomainStatusMemoryPercent() {
		return domainStatusMemoryPercent;
	}

	public void setDomainStatusMemoryPercent(double domainStatusMemoryPercent) {
		this.domainStatusMemoryPercent = domainStatusMemoryPercent;
	}

	@Override
	public String toString() {
		return "host";
	}

}
