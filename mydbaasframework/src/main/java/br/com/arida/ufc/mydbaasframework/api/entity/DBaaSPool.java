package main.java.br.com.arida.ufc.mydbaasframework.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.Host;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.VirtualMachine;

/**
 * @author Daivd Araújo
 * @version 2.0
 * @since April 1, 2013
 */
public abstract class DBaaSPool extends AbstractPool<DBaaS> {
	
	/**
	 * Method to retrieve the hosts of a particular DBaaS
	 * @param resource - DBaaS object owner
	 * @return a list of hosts of the DBaaS
	 */
	public abstract List<Host> getHosts(DBaaS resource);
	
	/**
	 * Method to retrieve the virtual machines of a particular DBaaS
	 * @param resource- DBaaS object owner
	 * @return a list of virtual machines of the DBaaS
	 */
	public abstract List<VirtualMachine> getMachines(DBaaS resource);

}
