package main.java.br.com.arida.ufc.mydbaasframework.common.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.metric.Machine;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.GenericResource;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 *
 */
public class VirtualMachine extends GenericResource {

	//key file if necessary for access
	private String key;
	//information system and machine
	private Machine information;
	//enviroment
	private DBaaS environment;
	//identifier in host
	private String identifierHost;
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
		
	public String getIdentifierHost() {
		return identifierHost;
	}

	public void setIdentifierHost(String identifierHost) {
		this.identifierHost = identifierHost;
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
