package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.LoadMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.InformationTable;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since July 11, 2013
 */
public class InformationTableMetric extends InformationTable implements LoadMetric {

	private static InformationTableMetric uniqueInstance;
	
	private InformationTableMetric() {}

	public static InformationTableMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new InformationTableMetric();
	    }
	    return uniqueInstance;
	}
	
	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("server")+properties.getProperty("informationTable.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("informationTable.cycle")));
		this.setEnabledDatabases(properties.getProperty("informationTable.databases"));
	}
}
