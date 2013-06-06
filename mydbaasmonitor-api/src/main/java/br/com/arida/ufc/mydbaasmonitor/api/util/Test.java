package main.java.br.com.arida.ufc.mydbaasmonitor.api.util;

import main.java.br.com.arida.ufc.mydbaasmonitor.api.client.MyDBaaSMonitorClient;
import main.java.br.com.arida.ufc.mydbaasmonitor.api.client.MyMetrics;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Cpu;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyDBaaSMonitorClient client = new MyDBaaSMonitorClient("http://localhost:8080/mydbaasmonitor");
		MyMetrics myMetrics = new MyMetrics(client);
//		Memory memory = (Memory) myMetrics.getMetricSingle("Memory", "machine", 5, null, null);
//		
//		System.out.println(memory.getRecordDate());
//		System.out.println(memory.getMemoryUsedPercent());
//		System.out.println(memory.getMemoryUsed());
//		System.out.println(memory.getMemorySwapUsedPercent());
//		System.out.println(memory.getMemorySwapUsed());
//		System.out.println(memory.getMemorySwapFreePercent());
//		System.out.println(memory.getMemorySwapFree());
//		System.out.println(memory.getMemoryFreePercent());
//		System.out.println(memory.getMemoryFree());
//		System.out.println(memory.getMemoryBuffersCacheUsed());
//		System.out.println(memory.getMemoryBuffersCacheFree());
//		
		for (Object metric : myMetrics.getMetricMulti("Cpu", "machine", 5, null, null)) {
			Cpu cpu = (Cpu) metric;
			System.out.println(cpu.getCpuCombined());
		}
		
//		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
//		parameters.add(new BasicNameValuePair("metricName", "Cpu"));
//		parameters.add(new BasicNameValuePair("resourceType", "machine"));
//		parameters.add(new BasicNameValuePair("metricType", "1"));
//		parameters.add(new BasicNameValuePair("resourceID", "5"));
//		parameters.add(new BasicNameValuePair("startDatetime", "05-06-2013 19:26:25"));
//		parameters.add(new BasicNameValuePair("endDatetime", "05-06-2013 19:26:35"));
//		
//		try {
//			HttpResponse response = SendResquest.postRequest("http://localhost:8080/mydbaasmonitor/metric/multi", parameters);
//			String string = SendResquest.getJsonResult(response);
//			System.out.println(string);
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
				
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
