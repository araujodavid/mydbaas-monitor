package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

/**
 * @author Daivd Araújo
 * @version 1.0
 * @since April 1, 2013
 */
public class DBaaSPool extends AbstractPool<DBaaS> {

	@Override
	public boolean save(DBaaS resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(DBaaS resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Host> getHosts(int dbaasId) {
		//TODO
		return null;
	}
	
	public List<VirtualMachine> getMachines(int dbaasId) {
		//TODO
		return null;
	}

}
