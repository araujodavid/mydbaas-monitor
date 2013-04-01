package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public class Cpu extends AbstractMetric {

	private double cpuUser;
	private double cpuSystem;	
	private double cpuNice;
	private double cpuWait;
	private double cpuIdle;
	private double cpuCombined;
	
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
	public String toString() {
		return "machine";
	}
}
