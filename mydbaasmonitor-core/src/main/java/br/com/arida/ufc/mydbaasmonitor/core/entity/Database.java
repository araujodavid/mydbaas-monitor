package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.GenericResource;

/**
 * @author David Araújo
 *
 */

public class Database extends GenericResource {
	
	//identify the type of DBMS
	private String type;
	//if the database belongs to a virtual machine
	private VirtualMachine virtualMachine;

	
	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

	public void setVirtualMachine(VirtualMachine virtualMachine) {
		this.virtualMachine = virtualMachine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "database";
	}

}
