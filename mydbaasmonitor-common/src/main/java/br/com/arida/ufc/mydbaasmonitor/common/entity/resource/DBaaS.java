package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 5.0
 * @since March 4, 2013
 * 
 */
public class DBaaS extends AbstractEntity {

	//list of hosts
	private List<Host> hosts;
	//list of virtual machines
	private List<VirtualMachine> machines;
	//list of hosts
	private List<DBMS> dbmss;
	//list of virtual machines
	private List<Database> databases;
	
	public List<VirtualMachine> getMachines() {
		return machines;
	}

	public void setMachines(List<VirtualMachine> machines) {
		this.machines = machines;
	}
	
	public List<Host> getHosts() {
		return hosts;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}
	
	public List<DBMS> getDbmss() {
		return dbmss;
	}

	public void setDbmss(List<DBMS> dbmss) {
		this.dbmss = dbmss;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	@Override
	public String toString() {
		return "dbaas";
	}

}
