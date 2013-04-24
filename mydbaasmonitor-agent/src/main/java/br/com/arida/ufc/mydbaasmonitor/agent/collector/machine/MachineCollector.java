package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.machine.MachineMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 5, 2013
 * 
 */

public class MachineCollector extends AbstractCollector<MachineMetric>  {

	public MachineCollector(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = MachineMetric.getInstance();
		OperatingSystem sys = OperatingSystem.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		CpuInfo cpuInfo = sigar.getCpuInfoList()[0];		
		this.metric.setMachineOperatingSystem(sys.getDescription());
		this.metric.setMachineKernelName(sys.getName());
		this.metric.setMachineKernelVersion(sys.getVersion());
		this.metric.setMachineArchitecture(sys.getArch());
		this.metric.setMachineTotalMemory(mem.getTotal());
		this.metric.setMachineTotalSwap(swap.getTotal());
		this.metric.setMachineTotalCPUCores(cpuInfo.getTotalCores());
		this.metric.setMachineTotalCPUSockets(cpuInfo.getTotalSockets());
		this.metric.setMachineTotalCoresPerSocket(cpuInfo.getCoresPerSocket());
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the System metric values (Sigar)");
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
