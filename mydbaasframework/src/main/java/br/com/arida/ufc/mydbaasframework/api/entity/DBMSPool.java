package main.java.br.com.arida.ufc.mydbaasframework.api.entity;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.Database;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class DBMSPool extends AbstractPool<DBMS> {

	/**
	 * Method to retrieve the databases of a particular DBMS
	 * @param resource - DBMS object owner
	 * @return a list of databases of the DBaaS
	 */
	public abstract List<Database> getDatabases(DBMS resource);

}
