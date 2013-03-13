package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.CpuCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MemoryCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.SOCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MemoryMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.SOMetric;

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
		//Loads the general information of the monitoring agent
		parser.loadProperties();
		
		//Collects information about the system and physical resources
		SOMetric soMetric = SOMetric.getInstance();
		soMetric.loadMetricProperties(parser.getProperties());		
		SOCollector soCollector = new SOCollector(parser.getIdentifier());
		soCollector.run();
		
		//Monitoring CPU
		if (parser.getProperties().getProperty("cpu.url") != null && !parser.getProperties().getProperty("cpu.url").equals("")) {
			CpuMetric cpuMetric = CpuMetric.getInstance();
			cpuMetric.loadMetricProperties(parser.getProperties());
			Timer cpuTimer = new Timer();
			CpuCollector cpuCollector = new CpuCollector(parser.getIdentifier());
			cpuTimer.scheduleAtFixedRate(cpuCollector, 0, 1*cpuMetric.getCyclo()*1000);
			
		}
		//Monitoring Memory
		if (parser.getProperties().getProperty("mem.url") != null && !parser.getProperties().getProperty("mem.url").equals("")) {
			MemoryMetric memoryMetric = MemoryMetric.getInstance();
			memoryMetric.loadMetricProperties(parser.getProperties());
			Timer memoryTimer = new Timer();
			MemoryCollector memoryCollector = new MemoryCollector(parser.getIdentifier());
			memoryTimer.scheduleAtFixedRate(memoryCollector, 0, 1*memoryMetric.getCyclo()*1000);	
		}
		
	}

}
