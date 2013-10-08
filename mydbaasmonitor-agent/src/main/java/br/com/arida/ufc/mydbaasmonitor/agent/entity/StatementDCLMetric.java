package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementDCL;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class StatementDCLMetric extends StatementDCL implements LoadMetric {

	private static StatementDCLMetric uniqueInstance;
	
	private StatementDCLMetric() {}

	public static StatementDCLMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StatementDCLMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("statementDCL.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("statementDCL.cycle")));
		this.setEnabledDBMSs(properties.getProperty("statementDCL.dbms"));
	}
}
