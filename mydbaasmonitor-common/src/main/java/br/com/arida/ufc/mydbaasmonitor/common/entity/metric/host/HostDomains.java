package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since June 1, 2013
 */
public class HostDomains extends AbstractMetric {
	
	private int hostDomainsInactive;
	private int hostDomainsActive;
	
	public int getHostDomainsInactive() {
		return hostDomainsInactive;
	}

	public void setHostDomainsInactive(int hostDomainsInactive) {
		this.hostDomainsInactive = hostDomainsInactive;
	}

	public int getHostDomainsActive() {
		return hostDomainsActive;
	}

	public void setHostDomainsActive(int hostDomainsActive) {
		this.hostDomainsActive = hostDomainsActive;
	}

	@Override
	public String toString() {
		return "host";
	}

	@Override
	public List<HostDomains> jsonToList(String json) {
		Gson gson = new Gson();
		List<HostDomains> hostDomainsList = gson.fromJson(json, new TypeToken<List<HostDomains>>(){}.getType());
		return hostDomainsList;
	}

}
