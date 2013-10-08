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
public class StatementDDL extends AbstractDatabaseMetric {

	private int statementDDLCreate;
	private int statementDDLAlter;
	private int statementDDLDrop;
	private int statementDDLTruncate;
	private int statementDDLRename;
	
	public int getStatementDDLCreate() {
		return statementDDLCreate;
	}

	public void setStatementDDLCreate(int statementDDLCreate) {
		this.statementDDLCreate = statementDDLCreate;
	}

	public int getStatementDDLAlter() {
		return statementDDLAlter;
	}

	public void setStatementDDLAlter(int statementDDLAlter) {
		this.statementDDLAlter = statementDDLAlter;
	}

	public int getStatementDDLDrop() {
		return statementDDLDrop;
	}

	public void setStatementDDLDrop(int statementDDLDrop) {
		this.statementDDLDrop = statementDDLDrop;
	}

	public int getStatementDDLTruncate() {
		return statementDDLTruncate;
	}

	public void setStatementDDLTruncate(int statementDDLTruncate) {
		this.statementDDLTruncate = statementDDLTruncate;
	}

	public int getStatementDDLRename() {
		return statementDDLRename;
	}

	public void setStatementDDLRename(int statementDDLRename) {
		this.statementDDLRename = statementDDLRename;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<StatementDDL> jsonToList(String json) {
		Gson gson = new Gson();
		List<StatementDDL> statementDDLList = gson.fromJson(json, new TypeToken<List<StatementDDL>>(){}.getType());
		return statementDDLList;
	}
}
