package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Memory;

/**
 * @author Daivd Araújo
 * @version 3.0
 * @since March 6, 2013
 */
public class MemoryMetric extends Memory implements LoadMetric {
	
	private static MemoryMetric uniqueInstance;
	
	private MemoryMetric() {}

	public static MemoryMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MemoryMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("memory.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("memory.cycle")));		
	}	
	
}
