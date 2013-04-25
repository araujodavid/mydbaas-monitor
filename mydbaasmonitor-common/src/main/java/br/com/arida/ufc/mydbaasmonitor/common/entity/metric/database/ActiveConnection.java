package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 17, 2013
 *
 */
public class ActiveConnection extends AbstractDatabaseMetric {

	private int activeConnectionAmount;
	
	public int getActiveConnectionAmount() {
		return activeConnectionAmount;
	}
	
	public void setActiveConnectionAmount(int activeConnectionAmount) {
		this.activeConnectionAmount = activeConnectionAmount;
	}

	@Override
	public String toString() {
		return "database";
	}
}
