package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;

/**
 * 
 * Class that will initialize the monitoring processes.
 * @author Daivd Araújo
 * @version 2.0
 * @since March 6, 2013
 * 
 */

public class MonitoringAgent {

	public static void main(String[] args) {
		MonitorInfoParser parser = MonitorInfoParser.getInstance();
		try {
			parser.loadContextFile("/home/david/Workspace MyDBaaSMonitor/mydbaasmonitor-agent/src/resources/context.conf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parser.loadProperties();
		System.out.println("Identifier: "+parser.getIdentifier());
		System.out.println("User: "+parser.getUser());
		System.out.println("Geral URL: "+parser.getGeralURL());
		System.out.println("Info URL: "+parser.getInformationURL());
		System.out.println("-------------------------------------");
		CpuMetric cpuMetric = CpuMetric.getInstance();
		cpuMetric.loadMetricProperties(parser.getProperties());
		System.out.println("Cpu URL: "+cpuMetric.getUrl());
		System.out.println("Cpu Cyclo: "+cpuMetric.getCyclo());
	}

}
