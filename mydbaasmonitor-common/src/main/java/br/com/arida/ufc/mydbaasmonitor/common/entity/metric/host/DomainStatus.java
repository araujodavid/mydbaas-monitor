package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 3.0
 * @since April 29, 2013
 */
public class DomainStatus extends AbstractMetric {

	private String domainStatusHostIdentifier;
	private double domainStatusCpuPercent;
	private double domainStatusMemoryPercent;
	
	public String getDomainStatusHostIdentifier() {
		return domainStatusHostIdentifier;
	}

	public void setDomainStatusHostIdentifier(String domainStatusHostIdentifier) {
		this.domainStatusHostIdentifier = domainStatusHostIdentifier;
	}

	public double getDomainStatusCpuPercent() {
		return domainStatusCpuPercent;
	}

	public void setDomainStatusCpuPercent(double domainStatusCpuPercent) {
		this.domainStatusCpuPercent = domainStatusCpuPercent;
	}

	public double getDomainStatusMemoryPercent() {
		return domainStatusMemoryPercent;
	}

	public void setDomainStatusMemoryPercent(double domainStatusMemoryPercent) {
		this.domainStatusMemoryPercent = domainStatusMemoryPercent;
	}

	@Override
	public String toString() {
		return "host";
	}

	@Override
	public List<DomainStatus> jsonToList(String json) {
		Gson gson = new Gson();
		List<DomainStatus> domainStatusList = gson.fromJson(json, new TypeToken<List<DomainStatus>>(){}.getType());
		return domainStatusList;
	}

}
