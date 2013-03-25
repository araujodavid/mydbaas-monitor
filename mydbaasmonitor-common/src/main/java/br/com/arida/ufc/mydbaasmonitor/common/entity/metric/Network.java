package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public class Network extends AbstractMetric {

	private long netBytesSent;
	private long netBytesReceived;
	private long netPacketsSent;
	private long netPacketsReceived;
	
	public long getNetBytesSent() {
		return netBytesSent;
	}

	public void setNetBytesSent(long netBytesSent) {
		this.netBytesSent = netBytesSent;
	}

	public long getNetBytesReceived() {
		return netBytesReceived;
	}

	public void setNetBytesReceived(long netBytesReceived) {
		this.netBytesReceived = netBytesReceived;
	}
	
	public long getNetPacketsSent() {
		return netPacketsSent;
	}

	public void setNetPacketsSent(long netPacketsSent) {
		this.netPacketsSent = netPacketsSent;
	}

	public long getNetPacketsReceived() {
		return netPacketsReceived;
	}

	public void setNetPacketsReceived(long netPacketsReceived) {
		this.netPacketsReceived = netPacketsReceived;
	}
}
