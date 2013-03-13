package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public class NetworkMetric extends AbstractMetric {
	
	private static NetworkMetric uniqueInstance;
	private long netBytesSent;
	private long netBytesReceived;
	private long netPacketsSent;
	private long netPacketsReceived;
	
	private NetworkMetric() {}

	public static NetworkMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NetworkMetric();
	    }
	    return uniqueInstance;
	}
	
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

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("geral.url")+properties.getProperty("net.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("net.cycle")));		
	}

}
