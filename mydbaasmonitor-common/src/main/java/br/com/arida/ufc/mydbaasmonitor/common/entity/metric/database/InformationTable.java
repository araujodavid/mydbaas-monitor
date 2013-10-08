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
public class InformationTable extends AbstractDatabaseMetric {

	private String informationTableName;
	private double informationTableDataLength;
	private double informationTableIndexLength;
	private long informationTableAmountRows;
	private double informationTableRowAverage;
	private double informationTableTotalSize;
	
	public String getInformationTableName() {
		return informationTableName;
	}

	public void setInformationTableName(String informationTableName) {
		this.informationTableName = informationTableName;
	}

	public double getInformationTableDataLength() {
		return informationTableDataLength;
	}

	public void setInformationTableDataLength(double informationTableDataLength) {
		this.informationTableDataLength = informationTableDataLength;
	}

	public double getInformationTableIndexLength() {
		return informationTableIndexLength;
	}

	public void setInformationTableIndexLength(double informationTableIndexLength) {
		this.informationTableIndexLength = informationTableIndexLength;
	}

	public long getInformationTableAmountRows() {
		return informationTableAmountRows;
	}

	public void setInformationTableAmountRows(long informationTableAmountRows) {
		this.informationTableAmountRows = informationTableAmountRows;
	}

	public double getInformationTableRowAverage() {
		return informationTableRowAverage;
	}

	public void setInformationTableRowAverage(double informationTableRowAverage) {
		this.informationTableRowAverage = informationTableRowAverage;
	}

	public double getInformationTableTotalSize() {
		return informationTableTotalSize;
	}

	public void setInformationTableTotalSize(double informationTableTotalSize) {
		this.informationTableTotalSize = informationTableTotalSize;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<InformationTable> jsonToList(String json) {
		Gson gson = new Gson();
		List<InformationTable> informationTableList = gson.fromJson(json, new TypeToken<List<InformationTable>>(){}.getType());
		return informationTableList;
	}
}
