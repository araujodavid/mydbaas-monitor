package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Machine;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.GenericResource;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 4, 2013
 *
 */
public class VirtualMachine extends GenericResource {

	//key file if necessary for access
	private String key;
	//information system and machine
	private Machine information;
	//enviroment
	private DBaaS environment;
	//host
	private Host host;
	//DBMSs list
	private List<DBMS> dbmsList;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Machine getInformation() {
		return information;
	}

	public void setInformation(Machine information) {
		this.information = information;
	}

	public DBaaS getEnvironment() {
		return environment;
	}

	public void setEnvironment(DBaaS environment) {
		this.environment = environment;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
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
