package main.java.br.com.arida.ufc.mydbaasmonitor.api.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.client.MyDBaaSMonitorClient;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.DBaaSPool;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyDBaaSMonitorClient client = new MyDBaaSMonitorClient("http://localhost:8080/mydbaasmonitor");
		DBaaSPool pool = client.getMyDBaaSs();
		for (DBaaS dBaaS : pool.getPool()) {
			System.out.println(dBaaS.getAlias());
			System.out.println(dBaaS.getRecordDate());
			System.out.println(dBaaS.getId());
			System.out.println(dBaaS.getDescription());
			for (VirtualMachine machine : dBaaS.getMachines()) {
				System.out.println(machine.getHost());
				System.out.println(machine.getAlias());
			}
		}
		
		try {
			HttpResponse response = SendResquest.postRequest("http://localhost:8080/mydbaasmonitor/pool/machines", null);
			String string = SendResquest.getJsonResult(response);
			System.out.println(string);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
