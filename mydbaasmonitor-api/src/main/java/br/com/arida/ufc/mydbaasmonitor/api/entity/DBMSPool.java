package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 28, 2013
 */

public class DBMSPool extends AbstractPool<DBMS> {

	@Override
	public boolean save(DBMS resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(DBMS resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Database> getDatabases(int dbmsId) {
		//TODO
		return null;
	}

}
