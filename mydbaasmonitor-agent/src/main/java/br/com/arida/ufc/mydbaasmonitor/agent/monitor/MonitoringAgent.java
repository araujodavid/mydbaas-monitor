package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.CpuCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.DiskCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MemoryCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.NetworkCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.DiskMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MachineMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MemoryMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.NetworkMetric;

/**
 * 
 * Class that will initialize the monitoring processes.
 * @author Daivd Araújo
 * @version 3.0
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
		MachineMetric machineMetric = MachineMetric.getInstance();
		machineMetric.loadMetricProperties(parser.getProperties());		
		MachineCollector machineCollector = new MachineCollector(parser.getIdentifier());
		machineCollector.run();
		
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
		//Monitoring Network
		if (parser.getProperties().getProperty("net.url") != null && !parser.getProperties().getProperty("net.url").equals("")) {
			NetworkMetric networkMetric = NetworkMetric.getInstance();
			networkMetric.loadMetricProperties(parser.getProperties());
			Timer networkTimer = new Timer();
			NetworkCollector networkCollector = new NetworkCollector(parser.getIdentifier());
			networkTimer.scheduleAtFixedRate(networkCollector, 0, 1*networkMetric.getCyclo()*1000);	
		}
		//Monitoring Disk
		if (parser.getProperties().getProperty("disk.url") != null && !parser.getProperties().getProperty("disk.url").equals("")) {
			DiskMetric diskMetric = DiskMetric.getInstance();
			diskMetric.loadMetricProperties(parser.getProperties());
			Timer diskTimer = new Timer();
			DiskCollector diskCollector = new DiskCollector(parser.getIdentifier());
			diskTimer.scheduleAtFixedRate(diskCollector, 0, 1*diskMetric.getCyclo()*1000);	
		}		
	}
	
	/**
	 * Method to check the metrics enabled in the config file
	 * @param properties
	 * @return a list with the names of the classes of metrics enabled
	 */
	public List<String> getEnabledMetrics(Properties properties) {
		List<String> enabledMetrics = new ArrayList<String>();
		//TODO
		return enabledMetrics;
	}

}
