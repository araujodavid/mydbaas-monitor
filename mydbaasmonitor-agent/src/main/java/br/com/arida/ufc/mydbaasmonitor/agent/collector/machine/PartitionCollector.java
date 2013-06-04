package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

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
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import com.sun.xml.internal.ws.util.StringUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.PartitionMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Partition;

/** 
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since June 1, 2013
 */
public class PartitionCollector extends AbstractCollector<PartitionMetric> {

	List<Partition> partitionMetrics;
	
	public PartitionCollector(int identifier) {
		super(identifier);
		this.partitionMetrics = new ArrayList<Partition>();
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {		
		Sigar sigar = (Sigar) args[0];
		this.metric = PartitionMetric.getInstance();
		FileSystem[] fileSystemList = sigar.getFileSystemList();
		FileSystemUsage fileSystemUsage;
		for (FileSystem fileSystem : fileSystemList) {
			if ((fileSystem.getDirName().trim().equals("/")) || (fileSystem.getDirName().trim().equals("/home"))) {
				Partition partition = new Partition();			
				partition.setPartitionDirectoryName(fileSystem.getDirName());
				partition.setPartitionDeviceName(fileSystem.getDevName());
				fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());
				partition.setPartitionReads(fileSystemUsage.getDiskReads());
				partition.setPartitionWrites(fileSystemUsage.getDiskWrites());
				partition.setPartitionBytesRead(fileSystemUsage.getDiskReadBytes());
				partition.setPartitionBytesWritten(fileSystemUsage.getDiskWriteBytes());
				partition.setPartitionFreeBytes(fileSystemUsage.getAvail());
				partition.setPartitionUsedBytes(fileSystemUsage.getUsed());
				partition.setPartitionTotalBytes(fileSystemUsage.getTotal());
				partition.setPartitionPercent(fileSystemUsage.getUsePercent()*100.0);
				this.partitionMetrics.add(partition);
			}			
		}
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();		
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});			
		} catch (SigarException e2) {
			System.out.println("Problem loading the Disk Partitions metric values (Sigar)");
			e2.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = null;
		try {
			params = this.loadRequestParams(new Date(), partitionMetrics);
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
				System.out.println("Partitions request error!");
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
		this.partitionMetrics.clear();
		sigar.close();
	}
	
	public List<NameValuePair> loadRequestParams(Date recordDate, List<Partition> partitionMetrics) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
		for (Partition partition : partitionMetrics) {			
			for (Field field : fields) {
				Method method;
				//Checks if the field is identified as a measure
				if (field.getName().toLowerCase().contains(extendedClazz.getSimpleName().toLowerCase())) {
					//Gets the get method of the field
					method = extendedClazz.getDeclaredMethod("get"+StringUtils.capitalize(field.getName()), null);
					//Adds the field and its value in the parameter list
					parameters.add(new BasicNameValuePair("metric["+i+"]."+field.getName(), String.valueOf(method.invoke(partition, null))));
				}			
			}
			i++;
		}
		return parameters;
	}

}
