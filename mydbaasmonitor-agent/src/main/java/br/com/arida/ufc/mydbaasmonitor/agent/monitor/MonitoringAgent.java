package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import com.sun.xml.internal.ws.util.StringUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.CpuCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MachineMetric;

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
		
		//TODO - Criar o loop que vai instânciar com collectors de forma automática com base no método getEnabledMetrics
	}
	
	/**
	 * Method to check the metrics enabled in the config file
	 * @param properties
	 * @return a list with the names of the classes of metrics enabled
	 */
	public List<String> getEnabledMetrics(Properties properties) {
		List<String> enabledMetrics = new ArrayList<String>();
		for (Object metric : properties.keySet()) {
			if ((metric.toString().contains(".url")) && (!properties.getProperty(metric.toString()).equals(""))) {
				String metricName = StringUtils.capitalize(metric.toString().replace(".url", "")).concat("Metric");
				enabledMetrics.add(metricName);
			}
		}
		return enabledMetrics;
	}

}
