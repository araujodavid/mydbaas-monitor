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
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.NetworkMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.server.SendResquest;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DateUtil;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 13, 2013
 * 
 */

public class NetworkCollector extends AbstractMachineCollector<NetworkMetric> {

	public NetworkCollector(int identifier) {
		this.machine = identifier;
	}

	@Override
	public void loadMetric(Sigar sigar) throws SigarException {
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
		
		this.metric.setNetBytesSent(bytesSent);
		this.metric.setNetBytesReceived(bytesReceived);
		this.metric.setNetPacketsSent(packetsSent);
		this.metric.setNetPacketsReceived(packetsReceived);
	}

	@Override
	public void run() {
		Sigar sigar = new Sigar();
		try {
			//Collecting metrics
			this.loadMetric(sigar);
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem loading the Net metric values (Sigar)");
			e.printStackTrace();
		}
				
		//Setting the parameters of the POST request
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("net.machine", String.valueOf(this.machine)));
		params.add(new BasicNameValuePair("net.netBytesSent", String.valueOf(this.metric.getNetBytesSent())));
		params.add(new BasicNameValuePair("net.netBytesReceived", String.valueOf(this.metric.getNetBytesReceived())));
		params.add(new BasicNameValuePair("net.netPacketsSent", String.valueOf(this.metric.getNetPacketsSent())));
		params.add(new BasicNameValuePair("net.netPacketsReceived", String.valueOf(this.metric.getNetPacketsReceived())));
		params.add(new BasicNameValuePair("net.recordDate", DateUtil.formatDate(new Date())));
		
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
