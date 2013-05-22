package main.java.br.com.arida.ufc.mydbaasframework.common.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.AbstractEntity;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 * 
 */
public class DBaaS extends AbstractEntity {

	//list of hosts
	private List<Host> hosts;
	//list of virtual machines
	private List<VirtualMachine> machines;
	
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

	@Override
	public String toString() {
		return "dbaas";
	}

}
