package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.NetworkTraffic;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class NetworkTrafficMetric extends NetworkTraffic implements LoadMetric {

	private static NetworkTrafficMetric uniqueInstance;
	
	private NetworkTrafficMetric() {}

	public static NetworkTrafficMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NetworkTrafficMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("networkTraffic.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("networkTraffic.cycle")));
		this.setEnabledDBMSs(properties.getProperty("networkTraffic.dbms"));
	}

}
