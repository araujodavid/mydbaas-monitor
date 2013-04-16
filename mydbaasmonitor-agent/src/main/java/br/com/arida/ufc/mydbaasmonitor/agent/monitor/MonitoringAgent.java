package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import com.sun.xml.internal.ws.util.StringUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.CpuCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MachineMetric;

/**
 * 
 * Class that will initialize the monitoring processes.
 * @author Daivd Araújo - @araujodavid
 * @version 4.0
 * @since March 6, 2013
 * 
 */

public class MonitoringAgent {
	
	private List<Timer> timers;
	private List<Object> collectors;
	
	/**
	 * Method to check the metrics enabled in the config file and creates the objects
	 * @param properties
	 * @return a list with the objects of the metrics enabled
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public List<Object> getEnabledMetrics(Properties properties) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Object> enabledMetrics = new ArrayList<Object>();
		for (Object metric : properties.keySet()) {
			if ((metric.toString().contains(".url")) && (!properties.getProperty(metric.toString()).equals(""))) {
				String metricName = StringUtils.capitalize(metric.toString().replace(".url", "")).concat("Metric");
				Class<?> metricClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity."+metricName);
				Method getInstance = metricClass.getDeclaredMethod("getInstance", null);				
				Object objectMetric = getInstance.invoke(null, null);
				Method loadMetricProperties = metricClass.getDeclaredMethod("loadMetricProperties", Properties.class);
				loadMetricProperties.invoke(objectMetric, new Object[] {properties});
				enabledMetrics.add(objectMetric);
			}
		}
		return enabledMetrics;
	}//getEnabledMetrics()
	
	public void startCollectors(List<Object> enabledMetrics, int identifier) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		this.timers = new ArrayList<Timer>();
		this.collectors = new ArrayList<Object>();
		for (Object object : enabledMetrics) {
			//
			Method getCyclo = object.getClass().getSuperclass().getSuperclass().getDeclaredMethod("getCyclo");
			int cyclo = (int) getCyclo.invoke(object, null);
			//
			String collectorName = StringUtils.capitalize(object.getClass().getSimpleName().replace("Metric", "")).concat("Collector");
			Class<?> collectorClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector."+object.toString()+"."+collectorName);
			Constructor constructor = collectorClass.getConstructors()[0];
			Object objectCollector = constructor.newInstance(new Object[] {identifier});
			//
			Timer timer = new Timer();			
			timer.scheduleAtFixedRate((TimerTask) objectCollector, 0, 1*cyclo*1000);
			//
			this.timers.add(timer);
			//
			this.collectors.add(objectCollector);
		}		
	}

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
}
