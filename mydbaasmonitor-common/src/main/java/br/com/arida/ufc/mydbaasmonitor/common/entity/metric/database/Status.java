package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 1, 2013
 *
 */
public class Status extends AbstractDatabaseMetric {

	private double statusCpuPercent;
	private double statusMemoryPercent;
	
	public double getStatusCpuPercent() {
		return statusCpuPercent;
	}

	public void setStatusCpuPercent(double statusCpuPercent) {
		this.statusCpuPercent = statusCpuPercent;
	}

	public double getStatusMemoryPercent() {
		return statusMemoryPercent;
	}

	public void setStatusMemoryPercent(double statusMemoryPercent) {
		this.statusMemoryPercent = statusMemoryPercent;
	}

	@Override
	public String toString() {
		return "database";
	}

}
