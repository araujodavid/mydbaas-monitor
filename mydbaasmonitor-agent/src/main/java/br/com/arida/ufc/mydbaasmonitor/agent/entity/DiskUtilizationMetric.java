package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.DiskUtilization;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class DiskUtilizationMetric extends DiskUtilization implements LoadMetric {

private static DiskUtilizationMetric uniqueInstance;
	
	private DiskUtilizationMetric() {}

	public static DiskUtilizationMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DiskUtilizationMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("diskUtilization.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("diskUtilization.cycle")));
		this.setEnabledDBMSs(properties.getProperty("diskUtilization.dbms"));
	}
}
