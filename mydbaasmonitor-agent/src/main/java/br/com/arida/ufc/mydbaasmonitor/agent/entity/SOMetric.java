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

public class SOMetric extends AbstractMetric {

	private static SOMetric uniqueInstance;
	private String operatingSystem;
	private String kernelName;
	private String kernelVersion;
	private String architecture;
	private long totalMemory;
	private long totalSwap;
	private int totalCPUCores;
	private int totalCPUSockets;
	private int totalCoresPerSocket;
	
	private SOMetric() {}

	public static SOMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SOMetric();
	    }
	    return uniqueInstance;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getKernelName() {
		return kernelName;
	}

	public void setKernelName(String kernelName) {
		this.kernelName = kernelName;
	}

	public String getKernelVersion() {
		return kernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		this.kernelVersion = kernelVersion;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public long getTotalSwap() {
		return totalSwap;
	}

	public void setTotalSwap(long totalSwap) {
		this.totalSwap = totalSwap;
	}

	public int getTotalCPUCores() {
		return totalCPUCores;
	}

	public void setTotalCPUCores(int totalCPUCores) {
		this.totalCPUCores = totalCPUCores;
	}

	public int getTotalCPUSockets() {
		return totalCPUSockets;
	}

	public void setTotalCPUSockets(int totalCPUSockets) {
		this.totalCPUSockets = totalCPUSockets;
	}

	public int getTotalCoresPerSocket() {
		return totalCoresPerSocket;
	}

	public void setTotalCoresPerSocket(int totalCoresPerSocket) {
		this.totalCoresPerSocket = totalCoresPerSocket;
	}

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("geral.url")+properties.getProperty("information.url"));		
	}	
	
}
