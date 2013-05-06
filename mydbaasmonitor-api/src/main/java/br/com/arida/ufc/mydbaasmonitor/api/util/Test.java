package main.java.br.com.arida.ufc.mydbaasmonitor.api.util;

import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.api.client.MyDBaaSMonitorClient;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyDBaaSMonitorClient client = new MyDBaaSMonitorClient("http://localhost:8080/mydbaasmonitor");
		List<String> metrics = client.getEnabledMetrics("machine");
		for (String string : metrics) {
			System.out.println(string);
		}
	}

}
