package main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.collector;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.client.MyDriverClient;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.manager.MyDBaaSConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.util.SendResquest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import com.sun.xml.internal.ws.util.StringUtils;

public abstract class AbstractWorkloadCollector<T extends AbstractDatabaseMetric> {
	
	protected String url;
	protected MyDriverClient client;
	protected MyDBaaSConnection myDBaaSConnection;
	
	/**
	 * Default constructor for classes heiresses.
	 * @param identifier - unique machine code
	 */
	public AbstractWorkloadCollector(MyDriverClient client, String url) {
		this.client = client;
		this.loadProperties(url);
		this.myDBaaSConnection = MyDBaaSConnection.getInstance();
	}
	
	/**
	 * 
	 * @param database
	 * @param workload
	 * @return an array with connection, statement, resultSet and metric
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public abstract Object[] executeQuery(Database database, String workload) throws ClassNotFoundException, SQLException, Exception;
	
	/**
	 * 
	 * @param database
	 * @param workload
	 * @return an array with connection, statement, resultSet and metric
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public abstract Object[] executeUpdate(Database database, String workload) throws ClassNotFoundException, SQLException, Exception;
	
	/**
	 * Method to load the HTTP request parameters of a metric.
	 * @param recordDate - datetime when the metric was collected
	 * @return a list of parameters and values ​​for the HTTP request
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public List<NameValuePair> loadRequestParams(T metric, Date recordDate, Database database) throws Exception {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		List<Field> fields = new ArrayList<Field>();
		
		//Gets the class of the metric
		Class<? extends AbstractDatabaseMetric> clazz = metric.getClass();
		//Gets the super class of the metric
		Class<?> extendedClazz = metric.getClass().getSuperclass();
		
		//Gets fields from the class
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		//Gets fields from the super class
		fields.addAll(Arrays.asList(extendedClazz.getDeclaredFields()));
		
		//Adds the machine identifier that belongs to the metric
		parameters.add(new BasicNameValuePair("database", String.valueOf(database.getId())));
		
		//Adds the datetime when the metric was collected
		parameters.add(new BasicNameValuePair("recordDate", this.formatDate(recordDate)));		
		
		//Creates HTTP request parameters from the fields of the metric
		for (Field field : fields) {
			Method method;
			//Checks if the field is identified as a measure
			if (field.getName().toLowerCase().contains(clazz.getSimpleName().toLowerCase())) {
				//Gets the get method of the field
				method = clazz.getDeclaredMethod("get"+StringUtils.capitalize(field.getName()), null);
				//Adds the field and its value in the parameter list
				parameters.add(new BasicNameValuePair("metric."+field.getName(), String.valueOf(method.invoke(metric, null))));
			}			
		}		
		return parameters;
	}
	
	/**
	 * Method to send the collected metrics to the server.
	 * @param parameters - list of measures of the metric
	 * @param charsetEncoding 
	 * @return an object http response
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResponse sendMetric(List<NameValuePair> parameters) throws Exception {
		HttpResponse response;
		response = SendResquest.postRequest(this.client.getServerUrl()+this.url, parameters, "UTF-8");
		return response;		
	}
	
	/**
	 * Method to get the metric's properties.
	 * @param properties
	 */
	protected void loadProperties(String url) {
		this.url = url;
	}
	
	protected String formatDate(Date date) {
		String formatedDate;
		Format formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatedDate = formatter.format(date);
		return formatedDate;		
	}
	
}
