package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.DomainStatus;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013
 * 
 */
public class DomainStatusMetric extends DomainStatus implements LoadMetric {

	private static DomainStatusMetric uniqueInstance;	
	
	private DomainStatusMetric() {}

	public static DomainStatusMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DomainStatusMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("domainStatus.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("domainStatus.cycle")));			
	}

}
