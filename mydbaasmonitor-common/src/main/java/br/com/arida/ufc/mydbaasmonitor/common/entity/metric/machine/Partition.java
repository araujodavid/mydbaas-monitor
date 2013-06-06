package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/** 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since June 1, 2013 
 */
public class Partition extends AbstractMetric {

	private String partitionDirectoryName;
	private String partitionDeviceName;
	private long partitionBytesRead;
	private long partitionBytesWritten;
	private long partitionReads;
	private long partitionWrites;
	private long partitionFreeBytes;
	private long partitionUsedBytes;
	private long partitionTotalBytes;
	private double partitionPercent;
	
	public String getPartitionDirectoryName() {
		return partitionDirectoryName;
	}

	public void setPartitionDirectoryName(String partitionDirectoryName) {
		this.partitionDirectoryName = partitionDirectoryName;
	}

	public String getPartitionDeviceName() {
		return partitionDeviceName;
	}

	public void setPartitionDeviceName(String partitionDeviceName) {
		this.partitionDeviceName = partitionDeviceName;
	}

	public long getPartitionBytesRead() {
		return partitionBytesRead;
	}

	public void setPartitionBytesRead(long partitionBytesRead) {
		this.partitionBytesRead = partitionBytesRead;
	}

	public long getPartitionBytesWritten() {
		return partitionBytesWritten;
	}

	public void setPartitionBytesWritten(long partitionBytesWritten) {
		this.partitionBytesWritten = partitionBytesWritten;
	}

	public long getPartitionReads() {
		return partitionReads;
	}

	public void setPartitionReads(long partitionReads) {
		this.partitionReads = partitionReads;
	}

	public long getPartitionWrites() {
		return partitionWrites;
	}

	public void setPartitionWrites(long partitionWrites) {
		this.partitionWrites = partitionWrites;
	}

	public long getPartitionFreeBytes() {
		return partitionFreeBytes;
	}

	public void setPartitionFreeBytes(long partitionFreeBytes) {
		this.partitionFreeBytes = partitionFreeBytes;
	}
	
	public long getPartitionUsedBytes() {
		return partitionUsedBytes;
	}

	public void setPartitionUsedBytes(long partitionUsedBytes) {
		this.partitionUsedBytes = partitionUsedBytes;
	}

	public long getPartitionTotalBytes() {
		return partitionTotalBytes;
	}

	public void setPartitionTotalBytes(long partitionTotalBytes) {
		this.partitionTotalBytes = partitionTotalBytes;
	}

	public double getPartitionPercent() {
		return partitionPercent;
	}

	public void setPartitionPercent(double partitionPercent) {
		this.partitionPercent = partitionPercent;
	}

	@Override
	public String toString() {
		return "machine";
	}

	@Override
	public List<Partition> jsonToList(String json) {
		Gson gson = new Gson();
		List<Partition> partitionList = gson.fromJson(json, new TypeToken<List<Partition>>(){}.getType());
		return partitionList;
	}
}
