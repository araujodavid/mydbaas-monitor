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
	
	//IP address of the resource
	private String host;
	//key file if necessary for access
	private String key;
	//information system and machine
	private OperatingSystem system;
	//enviroment
	private DBaaS environment;
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
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
	
	public DBaaS getEnvironment() {
		return environment;
	}

	public void setEnvironment(DBaaS environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "machine";
	}
}
