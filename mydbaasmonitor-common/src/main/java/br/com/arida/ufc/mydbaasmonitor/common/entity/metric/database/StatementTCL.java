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
public class StatementTCL extends AbstractDatabaseMetric {

	private int statementTCLCommits;
	private int statementTCLRollback;
	private int statementTCLSavepoint;
	
	public int getStatementTCLCommits() {
		return statementTCLCommits;
	}

	public void setStatementTCLCommits(int statementTCLCommits) {
		this.statementTCLCommits = statementTCLCommits;
	}

	public int getStatementTCLRollback() {
		return statementTCLRollback;
	}

	public void setStatementTCLRollback(int statementTCLRollback) {
		this.statementTCLRollback = statementTCLRollback;
	}

	public int getStatementTCLSavepoint() {
		return statementTCLSavepoint;
	}

	public void setStatementTCLSavepoint(int statementTCLSavepoint) {
		this.statementTCLSavepoint = statementTCLSavepoint;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<StatementTCL> jsonToList(String json) {
		Gson gson = new Gson();
		List<StatementTCL> statementTCLList = gson.fromJson(json, new TypeToken<List<StatementTCL>>(){}.getType());
		return statementTCLList;
	}
}
