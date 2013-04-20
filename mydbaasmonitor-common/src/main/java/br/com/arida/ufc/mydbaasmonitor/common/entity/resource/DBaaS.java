package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;

/** 
 * 
 * @author David Araújo
 * @version 2.0
 * @since March 4, 2013
 * 
 */
public class DBaaS extends AbstractEntity {

	//list of virtual machines
	private List<VirtualMachine> machines;
	
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
