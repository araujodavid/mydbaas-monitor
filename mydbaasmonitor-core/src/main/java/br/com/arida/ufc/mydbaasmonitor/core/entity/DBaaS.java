package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.AbstractEntity;

/** 
 * 
 * @author David Araújo
 * @version 2.0
 * @since March 4, 2013
 * 
 */

public class DBaaS extends AbstractEntity {
	
	//alias resource
	private String alias;
	//date of registration of the resource
	private String recordDate;
	//list of virtual machines
	private List<VirtualMachine> machines;
		
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	public List<VirtualMachine> getMachines() {
		return machines;
	}

	public void setMachines(List<VirtualMachine> machines) {
		this.machines = machines;
	}

	@Override
	public String toString() {
		return "dbaas";
	}	
}
