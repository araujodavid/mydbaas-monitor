package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.OperatingSystem;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.GenericResource;

/**
 * 
 * @author David Araújo
 * @version 3.0
 * @since March 4, 2013
 *
 */

public class VirtualMachine extends GenericResource {
	
	//key file if necessary for access
	private String key;
	private OperatingSystem system;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}		
	
	public OperatingSystem getSystem() {
		return system;
	}

	public void setSystem(OperatingSystem system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "machine";
	}
}
