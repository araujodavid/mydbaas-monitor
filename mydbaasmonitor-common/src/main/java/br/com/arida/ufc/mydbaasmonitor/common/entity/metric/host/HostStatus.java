package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 4.0
 * @since April 28, 2013
 */
public class HostStatus extends AbstractMetric {

	private double hostStatusCpuPercent;
	private double hostStatusMemoryPercent;
	private double hostStatusSwapPercent;
		
	public double getHostStatusCpuPercent() {
		return hostStatusCpuPercent;
	}

	public void setHostStatusCpuPercent(double hostStatusCpuPercent) {
		this.hostStatusCpuPercent = hostStatusCpuPercent;
	}

	public double getHostStatusMemoryPercent() {
		return hostStatusMemoryPercent;
	}

	public void setHostStatusMemoryPercent(double hostStatusMemoryPercent) {
		this.hostStatusMemoryPercent = hostStatusMemoryPercent;
	}

	public double getHostStatusSwapPercent() {
		return hostStatusSwapPercent;
	}

	public void setHostStatusSwapPercent(double hostStatusSwapPercent) {
		this.hostStatusSwapPercent = hostStatusSwapPercent;
	}

	@Override
	public String toString() {
		return "host";
	}

	@Override
	public List<HostStatus> jsonToList(String json) {
		Gson gson = new Gson();
		List<HostStatus> hostStatusList = gson.fromJson(json, new TypeToken<List<HostStatus>>(){}.getType());
		return hostStatusList;
	}

}
