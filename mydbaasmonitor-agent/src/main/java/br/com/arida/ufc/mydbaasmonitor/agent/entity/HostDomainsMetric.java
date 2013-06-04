package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.HostDomains;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since June 1, 2013 
 */
public class HostDomainsMetric extends HostDomains implements LoadMetric {

	private static HostDomainsMetric uniqueInstance;	
	
	private HostDomainsMetric() {}

	public static HostDomainsMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new HostDomainsMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("hostDomains.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("hostDomains.cycle")));		
	}

}
