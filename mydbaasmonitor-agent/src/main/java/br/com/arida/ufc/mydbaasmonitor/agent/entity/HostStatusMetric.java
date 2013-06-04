package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.HostStatus;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 30, 2013 
 */
public class HostStatusMetric extends HostStatus implements LoadMetric {

	private static HostStatusMetric uniqueInstance;	
	
	private HostStatusMetric() {}

	public static HostStatusMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new HostStatusMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("hostStatus.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("hostStatus.cycle")));
	}

}
