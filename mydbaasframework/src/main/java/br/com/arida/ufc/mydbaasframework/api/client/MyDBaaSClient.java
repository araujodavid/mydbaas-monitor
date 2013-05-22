package main.java.br.com.arida.ufc.mydbaasframework.api.client;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.DBMSPool;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.DBaaSPool;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.DatabasePool;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.HostPool;
import main.java.br.com.arida.ufc.mydbaasframework.api.entity.VirtualMachinePool;

/**
 * Class that handles the access information to the server.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */
public abstract class MyDBaaSClient {

	private String serverUrl;
	
	/**
	 * Default constructor
	 * @param serverUrl
	 */
	public MyDBaaSClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * Method to check server connection
	 * @return true if exitir, otherwise false
	 */
	public abstract boolean hasConnection();
	
	/**
	 * Method that returns the metrics available for resources
	 * @param typeResource (eg.: host, machine or database)
	 * @return a list of metric about the resource type
	 */
	public abstract List<String> getEnabledMetrics(String typeResource);
	
	public abstract DBaaSPool getMyDBaaSs();
	
	public abstract HostPool getMyHosts();
	
	public abstract VirtualMachinePool getMyVirtualMachines();
	
	public abstract DBMSPool getMyDBMSs();
	
	public abstract DatabasePool getMyDatabases();
	
	public abstract Object resourceLookupByID(int resourceId, String resourceType);

	//Getters and Setters
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	
	
}
