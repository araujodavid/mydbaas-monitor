package main.java.br.com.arida.ufc.mydbaasmonitor.api.client;

import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DBMSPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DBaaSPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DatabasePool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.HostPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.VirtualMachinePool;

/**
 * Class that handles the access information to the server.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 28, 2013
 */

public class MyDBaaSMonitorClient {

	private String serverUrl;
	
	/**
	 * Default constructor
	 * @param serverUrl
	 */
	public MyDBaaSMonitorClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * Method to check server connection
	 * @return true if exitir, otherwise false
	 */
	public boolean hasConnection() {
		//TODO
		return false;
	}
	
	public DBaaSPool getMyDBaaSs() {
		//TODO
		return null;
	}
	
	public HostPool getMyHosts() {
		//TODO
		return null;
	}
	
	public VirtualMachinePool getMyVirtualMachines() {
		//TODO
		return null;
	}
	
	public DBMSPool getMyDBMSs() {
		//TODO
		return null;
	}
	
	public DatabasePool getMyDatabases() {
		//TODO
		return null;		
	}

	//Getters and Setters
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	
	
}
