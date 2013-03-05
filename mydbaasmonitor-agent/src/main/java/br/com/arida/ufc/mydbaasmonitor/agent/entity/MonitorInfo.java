package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 5, 2013
 * 
 */

public class MonitorInfo {
	
	//Single instance of the object
	private static MonitorInfo uniqueInstance;
	//Unique identifier of the resource on the server
	private Integer identifier;
	//Time unit for the monitoring cycle (seconds)
	private Integer monitoringCycle;

	private MonitorInfo() {}

	public static MonitorInfo getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MonitorInfo();
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
