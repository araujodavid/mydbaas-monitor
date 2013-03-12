package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.common;

public abstract class AbstractMetric {

	private int machine;
	private String recordDate;
	
	public int getMachine() {
		return machine;
	}
	public void setMachine(int machine) {
		this.machine = machine;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}	
}
