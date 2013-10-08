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
public class StatementDML extends AbstractDatabaseMetric {

	private int statementDMLInserts;
	private int statementDMLSelects;
	private int statementDMLUpdates;
	private int statementDMLDeletes;
	
	public int getStatementDMLInserts() {
		return statementDMLInserts;
	}

	public void setStatementDMLInserts(int statementDMLInserts) {
		this.statementDMLInserts = statementDMLInserts;
	}

	public int getStatementDMLSelects() {
		return statementDMLSelects;
	}

	public void setStatementDMLSelects(int statementDMLSelects) {
		this.statementDMLSelects = statementDMLSelects;
	}

	public int getStatementDMLUpdates() {
		return statementDMLUpdates;
	}

	public void setStatementDMLUpdates(int statementDMLUpdates) {
		this.statementDMLUpdates = statementDMLUpdates;
	}

	public int getStatementDMLDeletes() {
		return statementDMLDeletes;
	}

	public void setStatementDMLDeletes(int statementDMLDeletes) {
		this.statementDMLDeletes = statementDMLDeletes;
	}

	@Override
	public String toString() {
		return "database";
	}
	
	@Override
	public List<StatementDML> jsonToList(String json) {
		Gson gson = new Gson();
		List<StatementDML> statementDMLList = gson.fromJson(json, new TypeToken<List<StatementDML>>(){}.getType());
		return statementDMLList;
	}
}
