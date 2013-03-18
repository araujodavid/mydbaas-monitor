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
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MemoryMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 5, 2013
 * 
 */

public class MemoryCollector extends AbstractMachineCollector<MemoryMetric>  {
	
	public MemoryCollector(int identifier) {
		this.machine = identifier;
	}
	
    private static Long format(long value) {
        return new Long(value / 1024);
    }
	
	@Override
	public void loadMetric(Sigar sigar) throws SigarException {
		this.metric = MemoryMetric.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();		
		this.metric.setSwapUsed(format(swap.getUsed()));
		this.metric.setSwapFree(format(swap.getFree()));
		this.metric.setMemoryUsed(format(mem.getUsed()));
		this.metric.setMemoryFree(format(mem.getFree()));
		this.metric.setMemoryUsedPercent(mem.getUsedPercent());
		this.metric.setMemoryFreePercent(mem.getFreePercent());
		this.metric.setBuffersCacheUsed(format(mem.getActualUsed()));
		this.metric.setBuffersCacheFree(format(mem.getActualFree()));
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		try {
			//Collecting metrics
			this.loadMetric(sigar);
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem loading the Memory metric values (Sigar)");
			e.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("machine", String.valueOf(machine)));
		params.add(new BasicNameValuePair("metric.swapUsed", String.valueOf(this.metric.getSwapUsed())));
		params.add(new BasicNameValuePair("metric.swapFree", String.valueOf(this.metric.getSwapFree())));
		params.add(new BasicNameValuePair("metric.memoryUsed", String.valueOf(this.metric.getMemoryUsed())));
		params.add(new BasicNameValuePair("metric.memoryFree", String.valueOf(this.metric.getMemoryFree())));
		params.add(new BasicNameValuePair("metric.memoryUsedPercent", String.valueOf(this.metric.getMemoryUsedPercent())));
		params.add(new BasicNameValuePair("metric.memoryFreePercent", String.valueOf(this.metric.getMemoryFreePercent())));
		params.add(new BasicNameValuePair("metric.buffersCacheUsed", String.valueOf(this.metric.getBuffersCacheUsed())));
		params.add(new BasicNameValuePair("metric.buffersCacheFree", String.valueOf(this.metric.getBuffersCacheFree())));
		params.add(new BasicNameValuePair("recordDate", DateUtil.formatDate(new Date())));
		
		HttpResponse response;
		
		try {
			response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
			System.out.println(response.getStatusLine());
			if (response.getStatusLine().getStatusCode() != 202) {
				System.out.println("Memory request error!");
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
