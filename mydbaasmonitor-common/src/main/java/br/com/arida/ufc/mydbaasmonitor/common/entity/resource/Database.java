package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since April 18, 2013
 * 
 */
public class Database extends AbstractEntity {

	private String name;	

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
