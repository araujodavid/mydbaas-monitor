package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since April 26, 2013
 *
 */
public class Size extends AbstractDatabaseMetric {

	private double sizeUsed;
	
	public double getSizeUsed() {
		return sizeUsed;
	}
	
	public void setSizeUsed(double sizeUsed) {
		this.sizeUsed = sizeUsed;
	}

	@Override
	public String toString() {
		return "database";
	}

}
