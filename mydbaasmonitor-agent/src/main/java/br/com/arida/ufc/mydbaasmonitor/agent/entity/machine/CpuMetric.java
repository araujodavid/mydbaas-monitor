package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Cpu;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 6, 2013
 * 
 */

public class CpuMetric extends Cpu implements LoadMetric {
	
	private static CpuMetric uniqueInstance;
	
	private CpuMetric() {}

	public static CpuMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CpuMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("cpu.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("cpu.cycle")));
	}	
}
