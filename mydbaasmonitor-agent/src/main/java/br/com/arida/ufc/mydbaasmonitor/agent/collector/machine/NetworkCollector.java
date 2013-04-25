package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.NetworkMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 13, 2013
 * 
 */

public class NetworkCollector extends AbstractCollector<NetworkMetric> {

	public NetworkCollector(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws SigarException {
		Sigar sigar = (Sigar) args[0];
		this.metric = NetworkMetric.getInstance();
		long bytesReceived = 0;
		long bytesSent = 0;
		long packetsSent = 0;
		long packetsReceived = 0;
		String[] netInterfacesList = sigar.getNetInterfaceList();
		
		for (String netInterface : netInterfacesList) {
			NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(netInterface);
			bytesReceived = bytesReceived + netInterfaceStat.getRxBytes();
			packetsReceived = packetsReceived + netInterfaceStat.getRxPackets();
			bytesSent = bytesSent + netInterfaceStat.getTxBytes();
			packetsSent = packetsSent + netInterfaceStat.getTxPackets();
		}
		
		this.metric.setNetworkBytesSent(bytesSent);
		this.metric.setNetworkBytesReceived(bytesReceived);
		this.metric.setNetworkPacketsSent(packetsSent);
		this.metric.setNetworkPacketsReceived(packetsReceived);
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		//Collecting metrics
		try {
			this.loadMetric(new Object[] {sigar});
		} catch (SigarException e2) {
			System.out.println("Problem loading the Network metric values (Sigar)");
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
				System.out.println("Net request error!");
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
