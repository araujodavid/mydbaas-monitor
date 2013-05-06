package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.GenericResource;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since March 22, 2013
 *
 */
public class Host extends GenericResource {

	//enviroment
	private DBaaS environment;	
	//list of virtual machines
	private List<VirtualMachine> machines;
	
	public DBaaS getEnvironment() {
		return environment;
	}

	public void setEnvironment(DBaaS environment) {
		this.environment = environment;
	}
	
	public List<VirtualMachine> getMachines() {
		return machines;
	}

	public void setMachines(List<VirtualMachine> machines) {
		this.machines = machines;
	}

	@Override
	public String toString() {
		return "host";
	}

}
