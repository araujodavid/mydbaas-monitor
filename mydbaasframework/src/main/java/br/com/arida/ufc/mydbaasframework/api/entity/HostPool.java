package main.java.br.com.arida.ufc.mydbaasframework.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.Host;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.VirtualMachine;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class HostPool extends AbstractPool<Host> {

	/**
	 * Method to retrieve the virtual machines of a particular Host
	 * @param resource- Host object owner
	 * @return a list of virtual machines of the Host
	 */
	public abstract List<VirtualMachine> getMachines(Host resource);

}
