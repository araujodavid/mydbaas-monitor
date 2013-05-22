package main.java.br.com.arida.ufc.mydbaasframework.agent.monitor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * 
 * Class that will initialize the monitoring processes.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 * 
 */

public class MonitoringAgent {
	
	private List<Timer> timers;
	private List<Object> collectors;
	
	public List<Timer> getTimers() {
		return timers;
	}

	public void setTimers(List<Timer> timers) {
		this.timers = timers;
	}

	public List<Object> getCollectors() {
		return collectors;
	}

	public void setCollectors(List<Object> collectors) {
		this.collectors = collectors;
	}

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
	public List<Object> getEnabledMetrics(Properties properties, String metricsPath) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		List<Object> enabledMetrics = new ArrayList<Object>();
		for (Object metric : properties.keySet()) {
			if ((metric.toString().contains(".url")) && (!properties.getProperty(metric.toString()).equals(""))) {
				String metricName = StringUtils.capitalize(metric.toString().replace(".url", "")).concat("Metric");
				Class<?> metricClass = Class.forName(metricsPath+"."+metricName);
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
	public void startCollectors(List<Object> enabledMetrics, int identifier, String collectorsPath) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException {
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
			Class<?> collectorClass = Class.forName(collectorsPath+"."+object.toString()+"."+collectorName);
			Constructor constructor = collectorClass.getConstructors()[0];
			Object objectCollector = constructor.newInstance(new Object[] {identifier});
			//The collector is scheduled and initiated
			Timer timer = new Timer();			
			timer.scheduleAtFixedRate((TimerTask) objectCollector, 0, 1*cyclo*1000);
			//Save the timer
			this.timers.add(timer);
			//Save the collector
			this.collectors.add(objectCollector);
		}		
	}//startCollectors()
}
