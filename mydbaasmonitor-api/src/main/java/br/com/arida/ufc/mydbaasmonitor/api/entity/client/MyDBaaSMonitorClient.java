package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.client;

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

	//Getters and Setters
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}	
	
}
