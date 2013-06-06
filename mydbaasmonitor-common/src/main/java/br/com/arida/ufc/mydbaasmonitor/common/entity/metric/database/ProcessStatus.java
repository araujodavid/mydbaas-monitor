package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;

/**
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since May 1, 2013
 */
public class ProcessStatus extends AbstractDatabaseMetric {

	private double processStatusCpu;
	private double processStatusMemory;
	
	public double getProcessStatusCpu() {
		return processStatusCpu;
	}

	public void setProcessStatusCpu(double processStatusCpu) {
		this.processStatusCpu = processStatusCpu;
	}

	public double getProcessStatusMemory() {
		return processStatusMemory;
	}

	public void setProcessStatusMemory(double processStatusMemory) {
		this.processStatusMemory = processStatusMemory;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<ProcessStatus> jsonToList(String json) {
		Gson gson = new Gson();
		List<ProcessStatus> processStatusList = gson.fromJson(json, new TypeToken<List<ProcessStatus>>(){}.getType());
		return processStatusList;
	}

}
