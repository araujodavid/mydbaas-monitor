package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.sun.xml.internal.ws.util.StringUtils;

import main.java.br.com.arida.ufc.mydbaasmonitor.api.client.MyDBaaSMonitorClient;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 28, 2013
 */
public abstract class AbstractPool<T extends AbstractEntity> {
	
	private List<T> pool;
	private MyDBaaSMonitorClient client;

	public List<T> getPool() {
		return pool;
	}

	public void setPool(List<T> pool) {
		this.pool = pool;
	}
	
	public MyDBaaSMonitorClient getClient() {
		return client;
	}

	public void setClient(MyDBaaSMonitorClient client) {
		this.client = client;
	}

	public abstract boolean save(T resource);
	
	public abstract boolean update(T resource);
	
	/**
	 * Method to check whether the object's resources is complete.
	 * Must be used before the save and update methods.
	 * @param resource
	 * @return true if entity is ok, otherwise false
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public boolean checkEntity(T resource) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Gets fields from the class
		for (Method method : getResourceMethods(resource)) {
			if (!method.getName().equals("getId")
				&& method.getName().startsWith("get")
				&& !method.getReturnType().getName().equals("java.util.List")) {
				Object result = method.invoke(resource, null);
				if (result == null) {
					return false;
				}
			}
		}
		return true;		
	}
	
	/**
	 * Method to load the HTTP request parameters of the resource.
	 * @param recordDate - datetime when the metric was collected
	 * @return a list of parameters and values ​​for the HTTP request
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public List<NameValuePair> loadRequestParams(T resource) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();		
		//Creates HTTP request parameters from the getters methods of the resource
		for (Method method : getResourceMethods(resource)) {
			//Checks if the method is a getter and is not of List type
			if (method.getName().startsWith("get") && !method.getReturnType().getName().equals("java.util.List")) {
				//Build the parameter name
				String paramName = method.getName().substring(3);
				paramName = paramName.substring(0, 1).toLowerCase().concat(paramName.substring(1));
				//Checks if the returned type is a resource owner, if so, it is necessary only to send the ID of this resource
				//Otherwise the field is sent is normally
				Class<?> methodReturnClass = method.getReturnType().getSuperclass();
				Method getIDMethod;
				Object resourceOwner;
				switch (methodReturnClass.getSimpleName()) {
				case "AbstractEntity":
					resourceOwner = method.invoke(resource, null);
					if (resourceOwner != null) {
						getIDMethod = methodReturnClass.getDeclaredMethod("getId", null);
						parameters.add(new BasicNameValuePair("resource."+paramName+".id", String.valueOf(getIDMethod.invoke(resourceOwner, null))));
					}
					break;
				case "GenericResource":
					resourceOwner = method.invoke(resource, null);
					if (resourceOwner != null) {
						getIDMethod = methodReturnClass.getSuperclass().getDeclaredMethod("getId", null);
						parameters.add(new BasicNameValuePair("resource."+paramName+".id", String.valueOf(getIDMethod.invoke(resourceOwner, null))));
					}
					break;
				case "AbstractMetric":
					Object resourceInfo = method.invoke(resource, null);
					if (resourceInfo != null) {
						for (Field infoField : resourceInfo.getClass().getDeclaredFields()) {
							Method infoMethod;
							if (infoField.getName().toLowerCase().contains(resourceInfo.getClass().getSimpleName().toLowerCase())) {
								infoMethod = resourceInfo.getClass().getDeclaredMethod("get"+StringUtils.capitalize(infoField.getName()), null);
								parameters.add(new BasicNameValuePair("resource."+paramName+"."+infoField.getName(), String.valueOf(infoMethod.invoke(resourceInfo, null))));
							}						
						}						
					}
					break;
				default:
					parameters.add(new BasicNameValuePair("resource."+paramName, String.valueOf(method.invoke(resource, null))));
					break;
				}
			}
		}
		return parameters;
	}
	
	private List<Method> getResourceMethods(T resource) {
		List<Method> methods = new ArrayList<Method>();
		Class<?> resourceClass = resource.getClass();
		Class<?> genericResourceClass = resourceClass.getSuperclass();
		Class<?> abstractEntityClass = genericResourceClass.getSuperclass();
		methods.addAll(Arrays.asList(resourceClass.getDeclaredMethods()));
		methods.addAll(Arrays.asList(genericResourceClass.getDeclaredMethods()));
		methods.addAll(Arrays.asList(abstractEntityClass.getDeclaredMethods()));
		return methods;
	}
}
