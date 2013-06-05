package main.java.br.com.arida.ufc.mydbaasmonitor.api.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MyDBaaSMonitorClient client = new MyDBaaSMonitorClient("http://localhost:8080/mydbaasmonitor");
//		DBaaSPool pool = client.getMyDBaaSs();
//		for (DBaaS dBaaS : pool.getPool()) {
//			System.out.println(dBaaS.getAlias());
//			System.out.println(dBaaS.getRecordDate());
//			System.out.println(dBaaS.getId());
//			System.out.println(dBaaS.getDescription());
//			for (VirtualMachine machine : dBaaS.getMachines()) {
//				System.out.println(machine.getHost());
//				System.out.println(machine.getAlias());
//			}
//		}
//		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("metricName", "Memory"));
		parameters.add(new BasicNameValuePair("resourceType", "machine"));
		parameters.add(new BasicNameValuePair("metricType", "1"));
		parameters.add(new BasicNameValuePair("resourceID", "3"));
		
		try {
			HttpResponse response = SendResquest.postRequest("http://10.41.18.148:8080/mydbaasmonitor/metric/single", parameters);
			String string = SendResquest.getJsonResult(response);
			System.out.println(string);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		for (Method method : VirtualMachine.class.getDeclaredMethods()) {
//			if (method.getName().startsWith("get") && !method.getReturnType().getName().equals("java.util.List")) {
//				System.out.println(method.getName());
//				System.out.println(method.getReturnType().getSuperclass().getSimpleName());
//			}
//		}
		
//		DBaaS dBaaS = new DBaaS();
//		dBaaS.setId(1);
//		dBaaS.setAlias("DBaaS 1");
//		dBaaS.setRecordDate(String.valueOf(new Date()));
//		dBaaS.setDescription("DBaaS Teste");
//		
//		Host host = new Host();
//		host.setId(2);
//		
//		Machine machineInfo = new Machine();
//		machineInfo.setMachineTotalSwap(200);
//		machineInfo.setMachineArchitecture("64");
//		machineInfo.setMachineTotalCPUCores(16);
//		machineInfo.setMachineTotalCPUSockets(4);
//		machineInfo.setMachineTotalMemory(5000);
//		
//		VirtualMachine machine = new VirtualMachine();
//		machine.setEnvironment(dBaaS);
//		machine.setHost(host);
//		machine.setAddress("127.0.0.1");
//		machine.setAlias("VM 1");
//		machine.setDescription("VM Teste");
//		machine.setIdentifierHost("one-120");
//		machine.setPassword("root");
//		machine.setPort(22);
//		machine.setRecordDate(String.valueOf(new Date()));
//		machine.setStatus(false);
//		machine.setUser("david");
//		machine.setInformation(machineInfo);
//		
//		
//		VirtualMachinePool pool = new VirtualMachinePool();
//		try {
//			for (NameValuePair pair : pool.loadRequestParams(machine)) {
//				System.out.println(pair.getName()+" - "+pair.getValue());
//			}
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
