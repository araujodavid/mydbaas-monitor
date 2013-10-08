package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementDML;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class StatementDMLMetric extends StatementDML implements LoadMetric {

	private static StatementDMLMetric uniqueInstance;
	
	private StatementDMLMetric() {}

	public static StatementDMLMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StatementDMLMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("statementDML.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("statementDML.cycle")));
		this.setEnabledDBMSs(properties.getProperty("statementDML.dbms"));
	}
}
