package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.DomainInfo;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013
 * 
 */
public class DomainInfoMetric extends DomainInfo implements LoadMetric {

	private static DomainInfoMetric uniqueInstance;	
	
	private DomainInfoMetric() {}

	public static DomainInfoMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DomainInfoMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		// TODO Auto-generated method stub		
	}

}
