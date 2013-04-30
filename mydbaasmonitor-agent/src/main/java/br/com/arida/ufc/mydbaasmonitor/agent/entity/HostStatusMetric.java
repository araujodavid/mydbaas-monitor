package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.HostStatus;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 30, 2013
 * 
 */
public class HostStatusMetric extends HostStatus implements LoadMetric {

	@Override
	public void loadMetricProperties(Properties properties) {
		// TODO Auto-generated method stub		
	}

}
