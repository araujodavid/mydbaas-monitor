package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ProcessStatus;

public class ProcessStatusMetric extends ProcessStatus implements LoadMetric {

	private static ProcessStatusMetric uniqueInstance;	
	
	private ProcessStatusMetric() {}

	public static ProcessStatusMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ProcessStatusMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("processStatus.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("processStatus.cycle")));
		this.setEnabledDBMSs(properties.getProperty("processStatus.dbms"));
	}

}
