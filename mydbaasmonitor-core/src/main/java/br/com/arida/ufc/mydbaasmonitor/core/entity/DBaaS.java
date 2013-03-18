package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.AbstractEntity;

/** 
 * 
 * @author David Araújo
 * @version 1.0
 * @since March 4, 2013
 * 
 */

public class DBaaS extends AbstractEntity {
	
	//alias resource
	private String alias;
	//date of registration of the resource
	private String recordDate;
		
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	@Override
	public String toString() {
		return "dbaas";
	}
	
}
