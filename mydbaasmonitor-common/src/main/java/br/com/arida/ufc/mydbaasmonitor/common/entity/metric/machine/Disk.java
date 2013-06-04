package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/** 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013 
 */
public class Disk extends AbstractMetric {

	private long diskBytesRead;
	private long diskBytesWritten;
	private long diskReads;
	private long diskWrites;
	private long diskFreeBytes;
	private long diskUsedBytes;
	private long diskTotalBytes;
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

	public long getDiskFreeBytes() {
		return diskFreeBytes;
	}

	public void setDiskFreeBytes(long diskFreeBytes) {
		this.diskFreeBytes = diskFreeBytes;
	}

	public long getDiskUsedBytes() {
		return diskUsedBytes;
	}

	public void setDiskUsedBytes(long diskUsedBytes) {
		this.diskUsedBytes = diskUsedBytes;
	}

	public long getDiskTotalBytes() {
		return diskTotalBytes;
	}

	public void setDiskTotalBytes(long diskTotalBytes) {
		this.diskTotalBytes = diskTotalBytes;
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
}
