package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MemoryMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 5, 2013
 * 
 */

public class MemoryCollector extends AbstractCollector<MemoryMetric>  {
	
	public MemoryCollector(int identifier) {
		super(identifier);
	}

	private static Long format(long value) {
        return new Long(value / 1024);
    }
	
	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = MemoryMetric.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();		
		this.metric.setMemorySwapUsed(format(swap.getUsed()));
		this.metric.setMemorySwapFree(format(swap.getFree()));
		this.metric.setMemoryUsed(format(mem.getUsed()));
		this.metric.setMemoryFree(format(mem.getFree()));
		this.metric.setMemoryUsedPercent(mem.getUsedPercent());
		this.metric.setMemoryFreePercent(mem.getFreePercent());
		this.metric.setMemoryBuffersCacheUsed(format(mem.getActualUsed()));
		this.metric.setMemoryBuffersCacheFree(format(mem.getActualFree()));
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the Memory metric values (Sigar)");
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
