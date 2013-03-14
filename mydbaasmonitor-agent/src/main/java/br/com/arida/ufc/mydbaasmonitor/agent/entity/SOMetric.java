package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.OperatingSystem;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 6, 2013
 * 
 */

public class SOMetric extends OperatingSystem implements LoadMetric {

	private static SOMetric uniqueInstance;
	
	private SOMetric() {}

	public static SOMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SOMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("geral.url")+properties.getProperty("information.url"));		
	}	
	
}
