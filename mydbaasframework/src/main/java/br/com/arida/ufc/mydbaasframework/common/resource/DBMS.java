package main.java.br.com.arida.ufc.mydbaasframework.common.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.GenericResource;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 17, 2013
 * 
 */
public class DBMS extends GenericResource {

	//virtual machine
	private VirtualMachine machine;
	//identify the type of DBMS
	private String type;
	//databases list
	private List<Database> databases;

	public VirtualMachine getMachine() {
		return machine;
	}

	public void setMachine(VirtualMachine machine) {
		this.machine = machine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
		
	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	@Override
	public String toString() {
		return "dbms";
	}
	
}
