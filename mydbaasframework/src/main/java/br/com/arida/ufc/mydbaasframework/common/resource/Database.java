package main.java.br.com.arida.ufc.mydbaasframework.common.resource;

import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.AbstractEntity;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 * 
 */
public class Database extends AbstractEntity {

	//DBMS
	private DBMS dbms;
	//schema name
	private String name;	

	public DBMS getDbms() {
		return dbms;
	}

	public void setDbms(DBMS dbms) {
		this.dbms = dbms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "database";
	}
}
