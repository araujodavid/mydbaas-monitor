package main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.util.SendResquest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class MyDriverClient {

	private String serverUrl;
	
	/**
	 * Default constructor
	 * @param serverUrl
	 */
	public MyDriverClient(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	/**
	 * Method to check server connection
	 * @return true if exitir, otherwise false
	 */
	public boolean hasConnection() {
		HttpResponse response = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/pool/connection", null, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() != 200) {
			return true;
		}
		return false;
	}
	
	public Database lookupDatabaseByID(Integer databaseId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("databaseId", String.valueOf(databaseId)));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.serverUrl+"/resource/find", params, "UTF-8");
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Database database = gson.fromJson(json, Database.class);
		return database;	
	}
	
	//Getters and Setters	
	public String getServerUrl() {
		return serverUrl;
	}
}
