package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DiskUtilization extends AbstractDatabaseMetric {
	
	private long diskUtilizationPhysicalReads;
	private long diskUtilizationLogicalReads;
	private long diskUtilizationPendingReads;
	private long diskUtilizationPendingWrites;
	private long diskUtilizationDataRead;
	private long diskUtilizationDataWritten;
	private long diskUtilizationPagesRead;
	private long diskUtilizationPagesWritten;
	private long diskUtilizationKeyRead;
	private long diskUtilizationKeyWrites;

	public long getDiskUtilizationPhysicalReads() {
		return diskUtilizationPhysicalReads;
	}

	public void setDiskUtilizationPhysicalReads(long diskUtilizationPhysicalReads) {
		this.diskUtilizationPhysicalReads = diskUtilizationPhysicalReads;
	}

	public long getDiskUtilizationLogicalReads() {
		return diskUtilizationLogicalReads;
	}

	public void setDiskUtilizationLogicalReads(long diskUtilizationLogicalReads) {
		this.diskUtilizationLogicalReads = diskUtilizationLogicalReads;
	}

	public long getDiskUtilizationPendingReads() {
		return diskUtilizationPendingReads;
	}

	public void setDiskUtilizationPendingReads(long diskUtilizationPendingReads) {
		this.diskUtilizationPendingReads = diskUtilizationPendingReads;
	}

	public long getDiskUtilizationPendingWrites() {
		return diskUtilizationPendingWrites;
	}

	public void setDiskUtilizationPendingWrites(long diskUtilizationPendingWrites) {
		this.diskUtilizationPendingWrites = diskUtilizationPendingWrites;
	}

	public long getDiskUtilizationDataRead() {
		return diskUtilizationDataRead;
	}

	public void setDiskUtilizationDataRead(long diskUtilizationDataRead) {
		this.diskUtilizationDataRead = diskUtilizationDataRead;
	}

	public long getDiskUtilizationDataWritten() {
		return diskUtilizationDataWritten;
	}

	public void setDiskUtilizationDataWritten(long diskUtilizationDataWritten) {
		this.diskUtilizationDataWritten = diskUtilizationDataWritten;
	}

	public long getDiskUtilizationPagesRead() {
		return diskUtilizationPagesRead;
	}

	public void setDiskUtilizationPagesRead(long diskUtilizationPagesRead) {
		this.diskUtilizationPagesRead = diskUtilizationPagesRead;
	}

	public long getDiskUtilizationPagesWritten() {
		return diskUtilizationPagesWritten;
	}

	public void setDiskUtilizationPagesWritten(long diskUtilizationPagesWritten) {
		this.diskUtilizationPagesWritten = diskUtilizationPagesWritten;
	}

	public long getDiskUtilizationKeyRead() {
		return diskUtilizationKeyRead;
	}

	public void setDiskUtilizationKeyRead(long diskUtilizationKeyRead) {
		this.diskUtilizationKeyRead = diskUtilizationKeyRead;
	}

	public long getDiskUtilizationKeyWrites() {
		return diskUtilizationKeyWrites;
	}

	public void setDiskUtilizationKeyWrites(long diskUtilizationKeyWrites) {
		this.diskUtilizationKeyWrites = diskUtilizationKeyWrites;
	}

	@Override
	public String toString() {
		return "database";
	}
	
	@Override
	public List<DiskUtilization> jsonToList(String json) {
		Gson gson = new Gson();
		List<DiskUtilization> diskUtilizationList = gson.fromJson(json, new TypeToken<List<DiskUtilization>>(){}.getType());
		return diskUtilizationList;
	}
}
