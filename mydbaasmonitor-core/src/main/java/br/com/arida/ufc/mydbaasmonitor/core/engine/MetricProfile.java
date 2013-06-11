package main.java.br.com.arida.ufc.mydbaasmonitor.core.engine;

import java.util.List;

public class MetricProfile {

	private String name;
	private String type;
	private int cycle;
	private List<Integer> dbms;
	private List<Integer> databases;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public List<Integer> getDbms() {
		return dbms;
	}

	public void setDbms(List<Integer> dbms) {
		this.dbms = dbms;
	}

	public List<Integer> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Integer> databases) {
		this.databases = databases;
	}
	
	public static String printList(List<Integer> list) {
		Integer last = list.get(list.size()-1);
		list.remove(last);
		String result = "";
		for (Integer integer : list) {
			result = result+String.valueOf(integer)+", ";
		}
		result = result+String.valueOf(last);
		return result;
	}	
}
