package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ActiveConnection;


/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 17, 2013
 */
public class ActiveConnectionMetric extends ActiveConnection implements LoadMetric {

	private static ActiveConnectionMetric uniqueInstance;	
	
	private ActiveConnectionMetric() {}

	public static ActiveConnectionMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ActiveConnectionMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("activeConnection.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("activeConnection.cycle")));
		this.setEnabledDBMSs(properties.getProperty("activeConnection.dbms"));
		this.setEnabledDatabases(properties.getProperty("activeConnection.databases"));
	}

}
