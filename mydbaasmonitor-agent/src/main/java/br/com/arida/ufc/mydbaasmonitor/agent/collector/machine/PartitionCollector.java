package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.PartitionMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Partition;

/** 
 * @author Daivd Araújo - @araujodavid
 * @version 3.0
 * @since June 1, 2013
 */
public class PartitionCollector extends AbstractCollector<PartitionMetric> {

	List<Partition> partitionMetrics;
	
	public PartitionCollector(int identifier, String type) {
		super(identifier, type);
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
			params = this.loadRequestParams(new Date(), partitionMetrics, 0, 0);
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
}
