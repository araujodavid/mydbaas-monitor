package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 9, 2013
 */
public class NetworkTraffic extends AbstractDatabaseMetric {

	private int networkTrafficBytesReceived;
	private int networkTrafficBytesSent;
	
	public int getNetworkTrafficBytesReceived() {
		return networkTrafficBytesReceived;
	}
	
	public void setNetworkTrafficBytesReceived(int networkTrafficBytesReceived) {
		this.networkTrafficBytesReceived = networkTrafficBytesReceived;
	}
	
	public int getNetworkTrafficBytesSent() {
		return networkTrafficBytesSent;
	}
	
	public void setNetworkTrafficBytesSent(int networkTrafficBytesSent) {
		this.networkTrafficBytesSent = networkTrafficBytesSent;
	}
	
	@Override
	public String toString() {
		return "database";
	}
	
	@Override
	public List<NetworkTraffic> jsonToList(String json) {
		Gson gson = new Gson();
		List<NetworkTraffic> networkTrafficList = gson.fromJson(json, new TypeToken<List<NetworkTraffic>>(){}.getType());
		return networkTrafficList;
	}
}
