package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common;

/** 
 * 
 * @author David Araújo
 * @version 2.0
 * @since March 4, 2013
 * 
 */

public abstract class GenericResource extends AbstractEntity {

	//port of access
	private Integer port;
	//user of access
	private String user;
	//user password
	private String password;
	//date of registration of the resource
	private String recordDate;
	//alias resource
	private String alias;
	//resource status
	private Boolean status;
	
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRecordDate() {
		return recordDate;
	}
	
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
