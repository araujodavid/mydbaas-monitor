package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common;

public abstract class GenericResource {

	//resource ID
	private Integer id;
	//IP address of the resource
	private String host;
	//port of access
	private Integer port;
	//user of access
	private String user;
	//user password
	private String password;
	//date of registration of the resource
	private String recordDate;
	//resource description
	private String description;
	//alias resource
	private String alias;
	//resource status
	private Boolean status;
	
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
