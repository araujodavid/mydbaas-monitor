package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.util.ArrayList;
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
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.DiskMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 13, 2013
 * 
 */

public class DiskCollector extends AbstractCollector<DiskMetric> {

	public DiskCollector(int identifier) {
		this.machine = identifier;
	}
	
	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = DiskMetric.getInstance();
		long diskTotal = 0;
		long diskUsed = 0;
		long diskFree = 0;
		long diskReadBytes = 0;
		long diskWriteBytes = 0;
		long diskReads = 0;
		long diskWrites = 0;
		FileSystem[] fileSystemList = sigar.getFileSystemList();
		FileSystemUsage fileSystemUsage;

		for (FileSystem fileSystem : fileSystemList) {
			fileSystemUsage = sigar.getFileSystemUsage(fileSystem.getDirName());
			diskTotal = diskTotal + fileSystemUsage.getTotal();
			diskUsed = diskUsed + fileSystemUsage.getUsed();
			diskFree = diskFree + fileSystemUsage.getFree();
			diskReadBytes = diskReadBytes + fileSystemUsage.getDiskReadBytes();
			diskWriteBytes = diskWriteBytes + fileSystemUsage.getDiskWriteBytes();
			diskReads = diskReads + fileSystemUsage.getDiskReads();
			diskWrites = diskWrites + fileSystemUsage.getDiskWrites();
		}

		this.metric.setDiskFree(diskFree);
		this.metric.setDiskUsed(diskUsed);
		this.metric.setDiskTotal(diskTotal);
		this.metric.setDiskReadBytes(diskReadBytes);
		this.metric.setDiskWriteBytes(diskWriteBytes);
		this.metric.setDiskReads(diskReads);
		this.metric.setDiskWrites(diskWrites);
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
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("machine", String.valueOf(this.machine)));
		params.add(new BasicNameValuePair("metric.diskUsed", String.valueOf(this.metric.getDiskUsed())));
		params.add(new BasicNameValuePair("metric.diskFree", String.valueOf(this.metric.getDiskFree())));
		params.add(new BasicNameValuePair("metric.diskTotal", String.valueOf(this.metric.getDiskTotal())));		
		params.add(new BasicNameValuePair("metric.diskReadBytes", String.valueOf(this.metric.getDiskReadBytes())));
		params.add(new BasicNameValuePair("metric.diskWriteBytes", String.valueOf(this.metric.getDiskWriteBytes())));
		params.add(new BasicNameValuePair("metric.diskReads", String.valueOf(this.metric.getDiskReads())));
		params.add(new BasicNameValuePair("metric.diskWrites", String.valueOf(this.metric.getDiskWrites())));		
		params.add(new BasicNameValuePair("recordDate", DateUtil.formatDate(new Date())));
		
		HttpResponse response;
		
		try {
			response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
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
