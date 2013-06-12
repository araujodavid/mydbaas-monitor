package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.host;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.HostStatusMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 30, 2013 
 */
public class HostStatusCollector extends AbstractCollector<HostStatusMetric> {

	public HostStatusCollector(int identifier, String type) {
		super(identifier, type);
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = HostStatusMetric.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		this.metric.setHostStatusMemoryPercent(Math.round(mem.getUsedPercent()*100.0)/100.0);
		this.metric.setHostStatusSwapPercent(Math.round((swap.getUsed()*100)/swap.getTotal()*100.0)/100.0);
		CpuPerc[] cpuPercs = sigar.getCpuPercList();
		double cpuAverage = 0;
		for (CpuPerc cpuPerc : cpuPercs) {
			cpuAverage = cpuAverage + Double.valueOf(CpuPerc.format(cpuPerc.getCombined()).replace("%", ""));
		}
		this.metric.setHostStatusCpuPercent(Math.round((cpuAverage/cpuPercs.length)*100.0)/100.0);
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the HostStatus metric values (Sigar)");
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
				System.out.println("HostStatus request error!");
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
