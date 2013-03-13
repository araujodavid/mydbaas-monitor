package main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity;

import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 13, 2013
 * 
 */

public class DiskMetric extends AbstractMetric {
	
	private static DiskMetric uniqueInstance;
	private long diskUsed;
	private long diskFree;
	private long diskTotal;	
	private long diskReadBytes;
	private long diskWriteBytes;
	private long diskReads;
	private long diskWrites;
	
	private DiskMetric() {}

	public static DiskMetric getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DiskMetric();
	    }
	    return uniqueInstance;
	}
	
	public long getDiskUsed() {
		return diskUsed;
	}

	public void setDiskUsed(long diskUsed) {
		this.diskUsed = diskUsed;
	}

	public long getDiskFree() {
		return diskFree;
	}

	public void setDiskFree(long diskFree) {
		this.diskFree = diskFree;
	}
	
	public long getDiskTotal() {
		return diskTotal;
	}

	public void setDiskTotal(long diskTotal) {
		this.diskTotal = diskTotal;
	}
	
	public long getDiskReadBytes() {
		return diskReadBytes;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
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

	@Override
	public void loadMetricProperties(Properties properties) {
		this.setUrl(properties.getProperty("geral.url")+properties.getProperty("disk.url"));
		this.setCyclo(Integer.parseInt(properties.getProperty("disk.cycle")));		
	}

}
