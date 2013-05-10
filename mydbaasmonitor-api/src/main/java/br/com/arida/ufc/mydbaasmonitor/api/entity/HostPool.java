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
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 28, 2013
 */

public class HostPool extends AbstractPool<Host> {

	@Override
	public boolean save(Host resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Host resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Method to retrieve the virtual machines of a particular Host
	 * @param resource- Host object owner
	 * @return a list of virtual machines of the Host
	 */
	public List<VirtualMachine> getMachines(Host resource) {
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
