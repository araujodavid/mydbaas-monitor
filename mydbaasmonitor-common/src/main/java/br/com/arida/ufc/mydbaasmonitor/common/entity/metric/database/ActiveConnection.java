package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;

/**
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since April 17, 2013
 */
public class ActiveConnection extends AbstractDatabaseMetric {

	private int activeConnectionAmount;
	
	public int getActiveConnectionAmount() {
		return activeConnectionAmount;
	}
	
	public void setActiveConnectionAmount(int activeConnectionAmount) {
		this.activeConnectionAmount = activeConnectionAmount;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<ActiveConnection> jsonToList(String json) {
		Gson gson = new Gson();
		List<ActiveConnection> processStatusList = gson.fromJson(json, new TypeToken<List<ActiveConnection>>(){}.getType());
		return processStatusList;
	}
}
