package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Machine;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.GenericResource;

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
	//information system and machine
	private Machine machine;
	//enviroment
	private DBaaS environment;
	//DBMSs list
	private List<DBMS> dbmsList;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}		
	
	public Machine getSystem() {
		return machine;
	}

	public void setSystem(Machine machine) {
		this.machine = machine;
	}
	
	public DBaaS getEnvironment() {
		return environment;
	}

	public void setEnvironment(DBaaS environment) {
		this.environment = environment;
	}
		
	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<DBMS> getDbmsList() {
		return dbmsList;
	}

	public void setDbmsList(List<DBMS> dbmsList) {
		this.dbmsList = dbmsList;
	}

	@Override
	public String toString() {
		return "machine";
	}

}
