package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

/**
 * 
 * @author Daivd Araújo
 * @version 1.1
 * @since March 5, 2013
 * 
 */

public class MonitorInfoParser {
	
	//Single instance of the object
	private static MonitorInfoParser uniqueInstance;
	//Unique identifier of the resource on the server
	private Integer identifier;
	//Time unit for the monitoring cycle (seconds)
	private Integer monitoringCycle;

	private MonitorInfoParser() {}

	public static MonitorInfoParser getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MonitorInfoParser();
	    }
	    return uniqueInstance;
	}

	public Integer getId() {
		return identifier;
	}

	public void setId(Integer identifier) {
		this.identifier = identifier;
	}

	public Integer getMonitoringCycle() {
		return monitoringCycle;
	}

	public void setMonitoringCycle(Integer monitoringCycle) {
		this.monitoringCycle = monitoringCycle;
	}	
	
}
