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
public class StatementDCL extends AbstractDatabaseMetric {

	private int statementDCLGrant;
	private int statementDCLRevoke;
	
	public int getStatementDCLGrant() {
		return statementDCLGrant;
	}

	public void setStatementDCLGrant(int statementDCLGrant) {
		this.statementDCLGrant = statementDCLGrant;
	}

	public int getStatementDCLRevoke() {
		return statementDCLRevoke;
	}

	public void setStatementDCLRevoke(int statementDCLRevoke) {
		this.statementDCLRevoke = statementDCLRevoke;
	}

	@Override
	public String toString() {
		return "database";
	}

	@Override
	public List<StatementDCL> jsonToList(String json) {
		Gson gson = new Gson();
		List<StatementDCL> statementDCLList = gson.fromJson(json, new TypeToken<List<StatementDCL>>(){}.getType());
		return statementDCLList;
	}	
}
