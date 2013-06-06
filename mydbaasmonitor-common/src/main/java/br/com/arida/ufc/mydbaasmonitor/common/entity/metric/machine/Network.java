package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since March 13, 2013
 */
public class Network extends AbstractMetric {

	private long networkBytesSent;
	private long networkBytesReceived;
	private long networkPacketsSent;
	private long networkPacketsReceived;
	
	public long getNetworkBytesSent() {
		return networkBytesSent;
	}
	
	public void setNetworkBytesSent(long networkBytesSent) {
		this.networkBytesSent = networkBytesSent;
	}
	
	public long getNetworkBytesReceived() {
		return networkBytesReceived;
	}
	
	public void setNetworkBytesReceived(long networkBytesReceived) {
		this.networkBytesReceived = networkBytesReceived;
	}
	
	public long getNetworkPacketsSent() {
		return networkPacketsSent;
	}
	
	public void setNetworkPacketsSent(long networkPacketsSent) {
		this.networkPacketsSent = networkPacketsSent;
	}
	
	public long getNetworkPacketsReceived() {
		return networkPacketsReceived;
	}
	
	public void setNetworkPacketsReceived(long networkPacketsReceived) {
		this.networkPacketsReceived = networkPacketsReceived;
	}

	@Override
	public String toString() {
		return "machine";
	}

	@Override
	public List<Network> jsonToList(String json) {
		Gson gson = new Gson();
		List<Network> networkList = gson.fromJson(json, new TypeToken<List<Network>>(){}.getType());
		return networkList;
	}	
}
