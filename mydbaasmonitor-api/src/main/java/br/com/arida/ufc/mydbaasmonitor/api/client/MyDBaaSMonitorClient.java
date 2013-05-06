package main.java.br.com.arida.ufc.mydbaasmonitor.api.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DBMSPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DBaaSPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DatabasePool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.HostPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.VirtualMachinePool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.util.SendResquest;

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
	
	/**
	 * Method that returns the metrics available for resources
	 * @param typeResource (eg.: host, machine or database)
	 * @return a list of metric about the resource type
	 */
	public List<String> getEnabledMetrics(String typeResource) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("metricsType", typeResource));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/metrics", params);
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		Gson gson = new Gson();
		List<String> metrics = gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
		return metrics;
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
