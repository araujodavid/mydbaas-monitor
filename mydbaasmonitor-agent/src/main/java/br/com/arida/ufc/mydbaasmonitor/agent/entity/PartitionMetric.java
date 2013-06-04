package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Partition;

/**
 * @author Daivd Araújo
 * @version 1.0
 * @since June 1, 2013
 */
public class PartitionMetric extends Partition implements LoadMetric {

	private static PartitionMetric uniqueInstance;
	
	private PartitionMetric() {}

	public static PartitionMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PartitionMetric();
	    }
	    return uniqueInstance;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("partition.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("partition.cycle")));		
	}
}
