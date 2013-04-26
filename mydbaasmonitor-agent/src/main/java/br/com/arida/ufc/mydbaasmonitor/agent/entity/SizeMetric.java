package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.Size;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 26, 2013
 * 
 */
public class SizeMetric extends Size implements LoadMetric {

	@Override
	public void loadMetricProperties(Properties properties) {
		// TODO Auto-generated method stub		
	}

}
