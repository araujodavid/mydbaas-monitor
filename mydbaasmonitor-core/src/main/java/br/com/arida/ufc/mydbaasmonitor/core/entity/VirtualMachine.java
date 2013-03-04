package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.GenericResource;

/**
 * @author David Araújo
 *
 */
public class VirtualMachine extends GenericResource {
	
	//key file if necessary for access
	private String key;
	//amount of memory
	private String memory;
	//type of operating system
	private String operatingSystem;
	//type of operating system kernel
	private String kernel;
	//amount of swap memory
	private String swap;
	//amount of cores
	private Integer cores;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}	
	
	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getKernel() {
		return kernel;
	}

	public void setKernel(String kernel) {
		this.kernel = kernel;
	}

	public String getSwap() {
		return swap;
	}

	public void setSwap(String swap) {
		this.swap = swap;
	}

	public Integer getCores() {
		return cores;
	}

	public void setCores(Integer cores) {
		this.cores = cores;
	}

	@Override
	public String toString() {
		return "machine";
	}
}
