package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.host;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.libvirt.Connect;
import org.libvirt.LibvirtException;
import org.libvirt.NodeInfo;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.HostInfoMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 3.0
 * @since April 30, 2013
 */
public class HostInfoCollector extends AbstractCollector<HostInfoMetric> {

	public HostInfoCollector(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws LibvirtException {
		this.metric = HostInfoMetric.getInstance();
		Connect connect = (Connect) args[0];
		NodeInfo nodeInfo = connect.nodeInfo();
		this.metric.setHostInfoName(connect.getHostName());
		this.metric.setHostInfoHypervisor(connect.getType());
		this.metric.setHostInfoCores(nodeInfo.cores);
		this.metric.setHostInfoCpus(nodeInfo.cpus);
		this.metric.setHostInfoMemory(Math.round(((nodeInfo.memory/1024)/1024)*100.0)/100.0);
		this.metric.setHostInfoMhz(nodeInfo.mhz);
		this.metric.setHostInfoModel(nodeInfo.model);
	}

	@Override
	public void run() {
		Connect connect = null;
		try {
			connect = new Connect(null);
			this.loadMetric(new Object[] {connect});
		} catch (LibvirtException e) {
			System.out.println("Problem loading the HostInfo metric values (Libvirt)");
			e.printStackTrace();
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
				System.out.println("HostInfo request error!");
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
		try {
			connect.close();
		} catch (LibvirtException e) {
			System.out.println("Problem to close the Libvirt connection.");
			e.printStackTrace();
		}
	}

}
