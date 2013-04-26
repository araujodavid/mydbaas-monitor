package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.Size;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 26, 2013
 * 
 */
public class SizeMetric extends Size implements LoadMetric {

	private static SizeMetric uniqueInstance;
	
	private SizeMetric() {}

	public static SizeMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SizeMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("size.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("size.cycle")));
		this.setEnabledDBMSs(properties.getProperty("size.dbms"));
		this.setEnabledDatabases(properties.getProperty("size.databases"));
	}

}
