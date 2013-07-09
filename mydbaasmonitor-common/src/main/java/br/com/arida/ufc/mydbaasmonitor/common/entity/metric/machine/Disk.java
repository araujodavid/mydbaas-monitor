package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/** 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since March 13, 2013 
 */
public class Disk extends AbstractMetric {

	private long diskBytesRead;
	private long diskBytesWritten;
	private long diskReads;
	private long diskWrites;
	private double diskFree;
	private double diskUsed;
	private double diskTotal;
	private double diskPercent;
		
	public long getDiskBytesRead() {
		return diskBytesRead;
	}

	public void setDiskBytesRead(long diskBytesRead) {
		this.diskBytesRead = diskBytesRead;
	}

	public long getDiskBytesWritten() {
		return diskBytesWritten;
	}

	public void setDiskBytesWritten(long diskBytesWritten) {
		this.diskBytesWritten = diskBytesWritten;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public long getDiskWrites() {
		return diskWrites;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}
	
	public double getDiskFree() {
		return diskFree;
	}

	public void setDiskFree(double diskFree) {
		this.diskFree = diskFree;
	}

	public double getDiskUsed() {
		return diskUsed;
	}

	public void setDiskUsed(double diskUsed) {
		this.diskUsed = diskUsed;
	}

	public double getDiskTotal() {
		return diskTotal;
	}

	public void setDiskTotal(double diskTotal) {
		this.diskTotal = diskTotal;
	}

	public double getDiskPercent() {
		return diskPercent;
	}

	public void setDiskPercent(double diskPercent) {
		this.diskPercent = diskPercent;
	}

	@Override
	public String toString() {
		return "machine";
	}

	@Override
	public List<Disk> jsonToList(String json) {
		Gson gson = new Gson();
		List<Disk> diskList = gson.fromJson(json, new TypeToken<List<Disk>>(){}.getType());
		return diskList;
	}
}
