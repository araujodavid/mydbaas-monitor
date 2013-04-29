package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 29, 2013
 * 
 */
public class DomainStatus extends AbstractMetric {

	private double domainStatusUsedCpu;
	private double domainStatusUsedMemory;
	
	public double getDomainStatusUsedCpu() {
		return domainStatusUsedCpu;
	}

	public void setDomainStatusUsedCpu(double domainStatusUsedCpu) {
		this.domainStatusUsedCpu = domainStatusUsedCpu;
	}

	public double getDomainStatusUsedMemory() {
		return domainStatusUsedMemory;
	}

	public void setDomainStatusUsedMemory(double domainStatusUsedMemory) {
		this.domainStatusUsedMemory = domainStatusUsedMemory;
	}

	@Override
	public String toString() {
		return "domain";
	}

}
