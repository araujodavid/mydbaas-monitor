package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 29, 2013
 * 
 */
public class DomainInfo extends AbstractMetric {

	private String domainInfoName;
	private double domainInfoMemory;
	private double domainInfoCpus;	
	
	public String getDomainInfoName() {
		return domainInfoName;
	}

	public void setDomainInfoName(String domainInfoName) {
		this.domainInfoName = domainInfoName;
	}

	public double getDomainInfoMemory() {
		return domainInfoMemory;
	}

	public void setDomainInfoMemory(double domainInfoMemory) {
		this.domainInfoMemory = domainInfoMemory;
	}

	public double getDomainInfoCpus() {
		return domainInfoCpus;
	}

	public void setDomainInfoCpus(double domainInfoCpus) {
		this.domainInfoCpus = domainInfoCpus;
	}

	@Override
	public String toString() {
		return "domain";
	}

}
