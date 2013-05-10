package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common.AbstractPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.util.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

/**
 * @author Daivd Araújo
 * @version 2.0
 * @since April 1, 2013
 */
public class DBaaSPool extends AbstractPool<DBaaS> {

	@Override
	public boolean save(DBaaS resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(DBaaS resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Method to retrieve the hosts of a particular DBaaS
	 * @param resource - DBaaS object owner
	 * @return a list of hosts of the DBaaS
	 */
	public List<Host> getHosts(DBaaS resource) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("identifier", String.valueOf(resource.getId())));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.getClient().getServerUrl()+"/resource/hosts", params);
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		List<Host> hosts = gson.fromJson(json, new TypeToken<List<Host>>(){}.getType());
		return hosts;
	}
	
	/**
	 * Method to retrieve the virtual machines of a particular DBaaS
	 * @param resource- DBaaS object owner
	 * @return a list of virtual machines of the DBaaS
	 */
	public List<VirtualMachine> getMachines(DBaaS resource) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("identifier", String.valueOf(resource.getId())));
		params.add(new BasicNameValuePair("ownerType", resource.toString()));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.getClient().getServerUrl()+"/resource/machines", params);
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		List<VirtualMachine> virtualMachines = gson.fromJson(json, new TypeToken<List<VirtualMachine>>(){}.getType());
		return virtualMachines;
	}

}
