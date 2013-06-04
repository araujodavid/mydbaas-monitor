package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.host;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import com.sun.xml.internal.ws.util.StringUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.DomainStatusMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.ShellCommand;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.DomainStatus;

/**
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since May 2, 2013
 */
public class DomainStatusCollector extends AbstractCollector<DomainStatusMetric> {

	private List<DomainStatus> domainStatusMetrics;
	
	public DomainStatusCollector(int identifier) {
		super(identifier);
		this.domainStatusMetrics = new ArrayList<DomainStatus>();
	}

	@Override
	public void loadMetric(Object[] args) throws LibvirtException {
		this.metric = DomainStatusMetric.getInstance();
		Connect connect = (Connect) args[0];
		int[] domains = connect.listDomains();
		for (int domainId : domains) {
			DomainStatus domainStatus = new DomainStatus();
			Domain domain = connect.domainLookupByID(domainId);
			domainStatus.setDomainStatusHostIdentifier(domain.getName());
			long domainPid = ShellCommand.getDomainPid(domain.getName());
			String[] results = ShellCommand.getProcessStatus(domainPid);
			domainStatus.setDomainStatusCpuPercent(Double.valueOf(results[0]));
			domainStatus.setDomainStatusMemoryPercent(Double.valueOf(results[1]));
			this.domainStatusMetrics.add(domainStatus);
		}
		
	}

	@Override
	public void run() {
		Connect connect = null;
		try {
			connect = new Connect(null);
			this.loadMetric(new Object[] {connect});
		} catch (LibvirtException e) {
			System.out.println("Problem loading the DomainStatus metric values (Libvirt)");
			e.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = null;
		try {
			params = this.loadRequestParams(new Date(), domainStatusMetrics);
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		for (NameValuePair nameValuePair : params) {
//			System.out.println(nameValuePair.getName()+" - "+nameValuePair.getValue());
//		}
		
		HttpResponse response;		
		try {
			response = this.sendMetric(params);
			System.out.println(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() != 202) {
				System.out.println("CPU request error!");
				EntityUtils.consume(response.getEntity());
			}
			EntityUtils.consume(response.getEntity());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Release any native resources associated with this sigar instance
		this.domainStatusMetrics.clear();
		try {
			connect.close();
		} catch (LibvirtException e) {
			System.out.println("Problem to close the Libvirt connection.");
			e.printStackTrace();
		}
	}
	
	public List<NameValuePair> loadRequestParams(Date recordDate, List<DomainStatus> domainStatusMetrics) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		parameters.add(new BasicNameValuePair("identifier", String.valueOf(this.identifier)));
		
		//Adds the datetime when the metric was collected
		parameters.add(new BasicNameValuePair("recordDate", DateUtil.formatDate(recordDate)));		
		
		//Creates HTTP request parameters from the fields of the metric
		int i = 0;
		for (DomainStatus domainStatus : domainStatusMetrics) {			
			for (Field field : fields) {
				Method method;
				//Checks if the field is identified as a measure
				if (field.getName().toLowerCase().contains(extendedClazz.getSimpleName().toLowerCase())) {
					//Gets the get method of the field
					method = extendedClazz.getDeclaredMethod("get"+StringUtils.capitalize(field.getName()), null);
					//Adds the field and its value in the parameter list
					parameters.add(new BasicNameValuePair("metric["+i+"]."+field.getName(), String.valueOf(method.invoke(domainStatus, null))));
				}			
			}
			i++;
		}
		return parameters;
	}

}
