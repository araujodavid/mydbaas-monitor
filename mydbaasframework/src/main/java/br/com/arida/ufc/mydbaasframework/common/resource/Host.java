package main.java.br.com.arida.ufc.mydbaasframework.common.resource;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.metric.HostInfo;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.GenericResource;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 *
 */
public class Host extends GenericResource {

	//enviroment
	private DBaaS environment;
	//information about host resources
	private HostInfo information;
	//list of virtual machines
	private List<VirtualMachine> machines;
	
	public DBaaS getEnvironment() {
		return environment;
	}

	public void setEnvironment(DBaaS environment) {
		this.environment = environment;
	}
	
	public HostInfo getInformation() {
		return information;
	}

	public void setInformation(HostInfo information) {
		this.information = information;
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
