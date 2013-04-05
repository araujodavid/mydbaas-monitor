package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Machine;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 6, 2013
 * 
 */

public class MachineMetric extends Machine implements LoadMetric {

	private static MachineMetric uniqueInstance;
	
	private MachineMetric() {}

	public static MachineMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MachineMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("geral.url")+properties.getProperty("information.url"));		
	}	
	
}
