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
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

/**
 * @author Daivd Araújo
 * @version 2.0
 * @since April 1, 2013
 */

public class VirtualMachinePool extends AbstractPool<VirtualMachine> {

	@Override
	public boolean save(VirtualMachine resource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VirtualMachine resource) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Method to retrieve the dbmss of a particular Virtual Machine
	 * @param resource- Virtual Machine object owner
	 * @return a list of dbmss of the Virtual Machine
	 */
	public List<DBMS> getDBMSs(VirtualMachine resource) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("identifier", String.valueOf(resource.getId())));
		HttpResponse response;
		String json = null;
		try {
			response = SendResquest.postRequest(this.getClient().getServerUrl()+"/resource/dbmss", params);
			json = SendResquest.getJsonResult(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		List<DBMS> dbmss = gson.fromJson(json, new TypeToken<List<DBMS>>(){}.getType());
		return dbmss;
	}

}
