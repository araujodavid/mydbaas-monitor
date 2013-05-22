package main.java.br.com.arida.ufc.mydbaasframework.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.VirtualMachine;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class VirtualMachinePool extends AbstractPool<VirtualMachine> {

	/**
	 * Method to retrieve the dbmss of a particular Virtual Machine
	 * @param resource- Virtual Machine object owner
	 * @return a list of dbmss of the Virtual Machine
	 */
	public abstract List<DBMS> getDBMSs(VirtualMachine resource);

}
