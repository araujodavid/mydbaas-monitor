package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 6, 2013
 * 
 */

public class CpuMetric extends AbstractMetric {
	
	private static CpuMetric uniqueInstance;
	private double cpuUser;
	private double cpuSystem;	
	private double cpuNice;
	private double cpuWait;
	private double cpuIdle;
	private double cpuCombined;
	
	private CpuMetric() {}

	public static CpuMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new CpuMetric();
	    }
	    return uniqueInstance;
	}
	
	public double getCpuUser() {
		return cpuUser;
	}
	public void setCpuUser(double cpuUser) {
		this.cpuUser = cpuUser;
	}
	public double getCpuSystem() {
		return cpuSystem;
	}
	public void setCpuSystem(double cpuSystem) {
		this.cpuSystem = cpuSystem;
	}
	public double getCpuNice() {
		return cpuNice;
	}
	public void setCpuNice(double cpuNice) {
		this.cpuNice = cpuNice;
	}
	public double getCpuWait() {
		return cpuWait;
	}
	public void setCpuWait(double cpuWait) {
		this.cpuWait = cpuWait;
	}
	public double getCpuIdle() {
		return cpuIdle;
	}
	public void setCpuIdle(double cpuIdle) {
		this.cpuIdle = cpuIdle;
	}
	public double getCpuCombined() {
		return cpuCombined;
	}
	public void setCpuCombined(double cpuCombined) {
		this.cpuCombined = cpuCombined;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("cpu.url"));
		this.setCyclo(properties.getProperty("cpu.cycle"));
	}	
}
