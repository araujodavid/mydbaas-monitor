package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity;

import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

/**
 * @author Daivd Araújo
 * @version 1.0
 * @since April 1, 2013
 */

public class VirtualMachinePool extends AbstractPool<VirtualMachine> {

	@Override
	public boolean save(VirtualMachine resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VirtualMachine resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<DBMS> getDBMSs(int virtualMachineId) {
		//TODO
		return null;
	}

}
