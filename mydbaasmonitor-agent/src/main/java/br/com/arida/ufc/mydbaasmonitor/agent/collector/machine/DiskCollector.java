package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.DiskMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 3.0
 * @since March 13, 2013
 */
public class DiskCollector extends AbstractCollector<DiskMetric> {

	public DiskCollector(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = DiskMetric.getInstance();
		long diskBytesRead = 0;
		long diskBytesWritten = 0;
		long diskReads = 0;
		long diskWrites = 0;
		long diskFreeBytes = 0;
		long diskUsedBytes = 0;
		long diskTotalBytes = 0;
		FileSystem[] fileSystemList = sigar.getFileSystemList();
		FileSystemUsage fileSystemUsage;

		for (FileSystem fileSystem : fileSystemList) {
			fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());
			diskBytesRead = diskBytesRead + fileSystemUsage.getDiskReadBytes();
			diskBytesWritten = diskBytesWritten + fileSystemUsage.getDiskWriteBytes();
			diskReads = diskReads + fileSystemUsage.getDiskReads();
			diskWrites = diskWrites + fileSystemUsage.getDiskWrites();
			diskFreeBytes = diskFreeBytes + fileSystemUsage.getAvail();
			diskUsedBytes = diskUsedBytes + fileSystemUsage.getUsed();
			diskTotalBytes = diskTotalBytes + fileSystemUsage.getTotal();
		}
		this.metric.setDiskReads(diskReads);
		this.metric.setDiskWrites(diskWrites);
		this.metric.setDiskBytesRead(diskBytesRead);
		this.metric.setDiskBytesWritten(diskBytesWritten);
		this.metric.setDiskFreeBytes(diskFreeBytes);
		this.metric.setDiskUsedBytes(diskUsedBytes);
		this.metric.setDiskTotalBytes(diskTotalBytes);
		this.metric.setDiskPercent(Math.round(((diskUsedBytes*100)/diskTotalBytes)*100.0)/100.0);
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the Disk metric values (Sigar)");
			e2.printStackTrace();
		}	
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = null;
		try {
			params = this.loadRequestParams(new Date(), 0, 0);
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
				System.out.println("Disk request error!");
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
		sigar.close();				
	}

}
