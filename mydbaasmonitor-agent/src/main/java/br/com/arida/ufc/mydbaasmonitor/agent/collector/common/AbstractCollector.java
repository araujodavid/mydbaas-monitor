package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.sun.xml.internal.ws.util.StringUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 25, 2013
 * 
 */
public abstract class AbstractCollector<T extends AbstractMetric> extends TimerTask {

	protected T metric;
	protected int machine;
	
	
	/**
	 * Default constructor for classes heiresses.
	 * @param identifier - unique machine code
	 */
	public AbstractCollector(int identifier) {
		machine = identifier;
	}
	
	/**
	 * Method that receives a parameter list and makes the collection of a particular metric.
	 * Method to load the entity metric values.
	 * @param args - a parameter list
	 * @throws Exception - it is possible change the type of exception
	 */
	public abstract void loadMetric(Object[] args) throws Exception;
	
	/**
	 * Method to load the HTTP request parameters of the metric.
	 * @param recordDate - datetime when the metric was collected
	 * @return a list of parameters and values ​​for the HTTP request
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public List<NameValuePair> loadRequestParams(Date recordDate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		List<Field> fields = new ArrayList<Field>();
		
		//Gets the class of the metric
		Class<? extends AbstractMetric> clazz = this.metric.getClass();
		//Gets the super class of the metric
		Class<?> extendedClazz = this.metric.getClass().getSuperclass();
		
		//Gets fields from the class
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		//Gets fields from the super class
		fields.addAll(Arrays.asList(extendedClazz.getDeclaredFields()));
		
		//Adds the machine identifier that belongs to the metric
		parameters.add(new BasicNameValuePair("machine", String.valueOf(this.machine)));
		//Adds the datetime when the metric was collected
		parameters.add(new BasicNameValuePair("recordDate", DateUtil.formatDate(recordDate)));		
		
		//Creates HTTP request parameters from the fields of the metric
		for (Field field : fields) {
			Method method;
			//Checks if the field is identified as a measure
			if (field.getName().toLowerCase().contains(extendedClazz.getSimpleName().toLowerCase())) {
				//Gets the get method of the field
				method = extendedClazz.getDeclaredMethod("get"+StringUtils.capitalize(field.getName()), null);
				//Adds the field and its value in the parameter list
				parameters.add(new BasicNameValuePair("metric."+field.getName(), String.valueOf(method.invoke(this.metric, null))));
			}			
		}		
		return parameters;
	}
	
	/**
	 * Method to create the log if a metric is not sent to the server
	 * @param recordDate - datetime when the metric was collected
	 */
	public void logMetric(Date recordDate) {
		// TODO Auto-generated method stub		
	}
	
	/**
	 * Method to send the collected metrics to the server.
	 * @param parameters - list of measures of the metric
	 * @param charsetEncoding 
	 * @return an object http response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse sendMetric(List<NameValuePair> parameters, String charsetEncoding) throws ClientProtocolException, IOException {
		HttpResponse response;
		response = SendResquest.postRequest(metric.getUrl(), parameters, charsetEncoding);
		return response;		
	}
}
