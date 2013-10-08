package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementTCL;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class StatementTCLMetric extends StatementTCL implements LoadMetric {

	private static StatementTCLMetric uniqueInstance;
	
	private StatementTCLMetric() {}

	public static StatementTCLMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StatementTCLMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("statementTCL.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("statementTCL.cycle")));
		this.setEnabledDBMSs(properties.getProperty("statementTCL.dbms"));
	}

}
