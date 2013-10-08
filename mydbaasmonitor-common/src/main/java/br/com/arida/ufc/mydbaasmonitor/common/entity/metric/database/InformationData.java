package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since July 11, 2013
 */
public class InformationData extends AbstractDatabaseMetric {

	private int informationDataDatabases;
	private int informationDataTables;
	private int informationDataIndexs;
	private int informationDataTriggers;
	private int informationDataViews;
	private int informationDataRoutines;
	
	public int getInformationDataDatabases() {
		return informationDataDatabases;
	}

	public void setInformationDataDatabases(int informationDataDatabases) {
		this.informationDataDatabases = informationDataDatabases;
	}

	public int getInformationDataTables() {
		return informationDataTables;
	}

	public void setInformationDataTables(int informationDataTables) {
		this.informationDataTables = informationDataTables;
	}

	public int getInformationDataIndexs() {
		return informationDataIndexs;
	}

	public void setInformationDataIndexs(int informationDataIndexs) {
		this.informationDataIndexs = informationDataIndexs;
	}

	public int getInformationDataTriggers() {
		return informationDataTriggers;
	}

	public void setInformationDataTriggers(int informationDataTriggers) {
		this.informationDataTriggers = informationDataTriggers;
	}

	public int getInformationDataViews() {
		return informationDataViews;
	}

	public void setInformationDataViews(int informationDataViews) {
		this.informationDataViews = informationDataViews;
	}

	public int getInformationDataRoutines() {
		return informationDataRoutines;
	}

	public void setInformationDataRoutines(int informationDataRoutines) {
		this.informationDataRoutines = informationDataRoutines;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<InformationData> jsonToList(String json) {
		Gson gson = new Gson();
		List<InformationData> informationDataList = gson.fromJson(json, new TypeToken<List<InformationData>>(){}.getType());
		return informationDataList;
	}

}
