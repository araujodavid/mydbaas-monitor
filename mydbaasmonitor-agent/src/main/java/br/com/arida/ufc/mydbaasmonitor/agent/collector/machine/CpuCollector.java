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
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 5, 2013
 * 
 */

public class CpuCollector extends AbstractMachineCollector<CpuMetric> {
	
	public CpuCollector(int identifier) {
		this.machine = identifier;
	}
	
	@Override
	public void loadMetric(Sigar sigar) throws SigarException {
		this.metric = CpuMetric.getInstance();
		CpuPerc cpuPerc = sigar.getCpuPerc();		
		this.metric.setCpuUser(cpuPerc.getUser());
		this.metric.setCpuSystem(cpuPerc.getSys());
		this.metric.setCpuIdle(cpuPerc.getIdle());
		this.metric.setCpuNice(cpuPerc.getNice());
		this.metric.setCpuWait(cpuPerc.getWait());
		this.metric.setCpuCombined(cpuPerc.getCombined());
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		try {
			//Collecting metrics
			this.loadMetric(sigar);
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem loading the CPU metric values (Sigar)");
			e.printStackTrace();
		}
		
		//Setting the parameters of the POST request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("machine", String.valueOf(this.machine)));
		params.add(new BasicNameValuePair("metric.cpuUser", String.valueOf(this.metric.getCpuUser())));
		params.add(new BasicNameValuePair("metric.cpuSystem", String.valueOf(this.metric.getCpuSystem())));
		params.add(new BasicNameValuePair("metric.cpuIdle", String.valueOf(this.metric.getCpuIdle())));
		params.add(new BasicNameValuePair("metric.cpuNice", String.valueOf(this.metric.getCpuNice())));
		params.add(new BasicNameValuePair("metric.cpuWait", String.valueOf(this.metric.getCpuWait())));
		params.add(new BasicNameValuePair("metric.cpuCombined", String.valueOf(this.metric.getCpuCombined())));
		params.add(new BasicNameValuePair("recordDate", DateUtil.formatDate(new Date())));
		
		HttpResponse response;
		
		try {
			response = SendResquest.postRequest(this.metric.getUrl(), params, "UTF-8");
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
		sigar.close();
	}	

}
