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

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.host.HostInfoCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.MachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.HostInfoMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MachineMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;

/**
 * Class that will initialize the monitoring processes.
 * @author Daivd Araújo - @araujodavid
 * @version 5.0
 * @since March 6, 2013
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
	
	/**
	 * Method for instantiating the collectors of metrics enabled
	 * @param enabledMetrics - list with the objects of the enabled metrics
	 * @param identifier - resource id
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	public void startCollectors(List<Object> enabledMetrics, int identifier, String type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		this.timers = new ArrayList<Timer>();
		this.collectors = new ArrayList<Object>();
		for (Object object : enabledMetrics) {
			Method getCyclo = null;
			//Gets the value of the metric collection cycle
			if (object.toString().equals("database")) {
				getCyclo = object.getClass().getSuperclass().getSuperclass().getSuperclass().getDeclaredMethod("getCyclo");
			} else {
				getCyclo = object.getClass().getSuperclass().getSuperclass().getDeclaredMethod("getCyclo");
			}			
			int cyclo = (int) getCyclo.invoke(object, null);
			//Based on the metric object is created its collector
			String collectorName = StringUtils.capitalize(object.getClass().getSimpleName().replace("Metric", "")).concat("Collector");
			Class<?> collectorClass = Class.forName("main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector."+object.toString()+"."+collectorName);
			Constructor constructor = collectorClass.getConstructors()[0];
			Object objectCollector = constructor.newInstance(new Object[] {identifier, type});
			//The collector is scheduled and initiated
			Timer timer = new Timer();			
			timer.scheduleAtFixedRate((TimerTask) objectCollector, 0, 1*cyclo*1000);
			//Save the timer
			this.timers.add(timer);
			//Save the collector
			this.collectors.add(objectCollector);
		}		
	}

	public static void main(String[] args) {
		MonitorInfoParser parser = MonitorInfoParser.getInstance();
		try {
			parser.loadContextFile("/home/david/Workspace MyDBaaSMonitor/mydbaasmonitor-agent/src/resources/host.conf");
			//parser.loadContextFile(String.valueOf(args[0])+"/MyDBaaSMonitor/context.conf");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Loads the general information of the monitoring agent
		parser.loadProperties();
		
		//Create a MonitoringAgent object
		MonitoringAgent agent = new MonitoringAgent();
		
		//Creates and starts managing access to databases
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		databaseConnection.loadDBMSProperties(parser.getProperties());
		
		if (parser.getType().equals("host")) {
//			//Collects information about the system and physical resources
//			HostInfoMetric hostInfoMetric = HostInfoMetric.getInstance();
//			hostInfoMetric.loadMetricProperties(parser.getProperties());		
//			HostInfoCollector hostInfoCollector = new HostInfoCollector(parser.getIdentifier(), parser.getType());
//			hostInfoCollector.run();
			System.out.println("Não coletou a info.");
		} else if (parser.getType().equals("machine")) {
			//Collects information about the system and physical resources
			MachineMetric machineMetric = MachineMetric.getInstance();
			machineMetric.loadMetricProperties(parser.getProperties());		
			MachineCollector machineCollector = new MachineCollector(parser.getIdentifier(), parser.getType());
			machineCollector.run();
		}		
		
		try {
			//Get enabled metric in the conf file
			List<Object>  enabledMetrics = agent.getEnabledMetrics(parser.getProperties());
			//Create the collectos
			agent.startCollectors(enabledMetrics, parser.getIdentifier(), parser.getType());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
//		//Monitoring CPU
//		if (parser.getProperties().getProperty("cpu.url") != null && !parser.getProperties().getProperty("cpu.url").equals("")) {
//			CpuMetric cpuMetric = CpuMetric.getInstance();
//			cpuMetric.loadMetricProperties(parser.getProperties());
//			Timer cpuTimer = new Timer();
//			CpuCollector cpuCollector = new CpuCollector(parser.getIdentifier());
//			cpuTimer.scheduleAtFixedRate(cpuCollector, 0, 1*cpuMetric.getCyclo()*1000);
//		}		
	}
}
