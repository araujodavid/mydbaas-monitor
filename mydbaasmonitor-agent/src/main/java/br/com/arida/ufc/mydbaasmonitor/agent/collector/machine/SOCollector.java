package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.SOMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 5, 2013
 * 
 */

public class SOCollector extends AbstractMachineCollector<SOMetric>  {

	public SOCollector(int identifier) {
		this.machine = identifier;
	}
	
	@Override
	public void loadMetric(Sigar sigar) throws SigarException {
		this.metric = SOMetric.getInstance();
		OperatingSystem sys = OperatingSystem.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		CpuInfo cpuInfo = sigar.getCpuInfoList()[0];		
		this.metric.setOperatingSystem(sys.getDescription());
		this.metric.setKernelName(sys.getName());
		this.metric.setKernelVersion(sys.getVersion());
		this.metric.setArchitecture(sys.getArch());
		this.metric.setTotalMemory(mem.getTotal());
		this.metric.setTotalSwap(swap.getTotal());
		this.metric.setTotalCPUCores(cpuInfo.getTotalCores());
		this.metric.setTotalCPUSockets(cpuInfo.getTotalSockets());
		this.metric.setTotalCoresPerSocket(cpuInfo.getCoresPerSocket());
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		try {
			//Collecting metrics
			this.loadMetric(sigar);
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem loading the System metric values (Sigar)");
			e.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("machine", String.valueOf(this.machine)));
		params.add(new BasicNameValuePair("metric.operatingSystem", String.valueOf(this.metric.getOperatingSystem())));
		params.add(new BasicNameValuePair("metric.kernelName", String.valueOf(this.metric.getKernelName())));
		params.add(new BasicNameValuePair("metric.kernelVersion", String.valueOf(this.metric.getKernelVersion())));
		params.add(new BasicNameValuePair("metric.architecture", String.valueOf(this.metric.getArchitecture())));
		params.add(new BasicNameValuePair("metric.totalMemory", String.valueOf(this.metric.getTotalMemory())));
		params.add(new BasicNameValuePair("metric.totalSwap", String.valueOf(this.metric.getTotalSwap())));
		params.add(new BasicNameValuePair("metric.totalCPUCores", String.valueOf(this.metric.getTotalCPUCores())));
		params.add(new BasicNameValuePair("metric.totalCPUSockets", String.valueOf(this.metric.getTotalCPUSockets())));
		params.add(new BasicNameValuePair("metric.totalCoresPerSocket", String.valueOf(this.metric.getTotalCoresPerSocket())));
		
		HttpResponse response;
		
		try {
			response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
			System.out.println(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() != 202) {
				System.out.println("System request error!");
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
