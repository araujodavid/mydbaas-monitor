package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 13, 2013
 * 
 */

public class Memory extends AbstractMetric {

	private long memorySwapUsed;
	private long memorySwapFree;
	private long memoryUsed;
	private long memoryFree;
	private double memoryUsedPercent;
	private double memoryFreePercent;
	private long memoryBuffersCacheUsed;
	private long memoryBuffersCacheFree;
	
	public long getMemorySwapUsed() {
		return memorySwapUsed;
	}
	
	public void setMemorySwapUsed(long memorySwapUsed) {
		this.memorySwapUsed = memorySwapUsed;
	}
	
	public long getMemorySwapFree() {
		return memorySwapFree;
	}
	
	public void setMemorySwapFree(long memorySwapFree) {
		this.memorySwapFree = memorySwapFree;
	}
	
	public long getMemoryUsed() {
		return memoryUsed;
	}
	
	public void setMemoryUsed(long memoryUsed) {
		this.memoryUsed = memoryUsed;
	}
	
	public long getMemoryFree() {
		return memoryFree;
	}
	
	public void setMemoryFree(long memoryFree) {
		this.memoryFree = memoryFree;
	}
	
	public double getMemoryUsedPercent() {
		return memoryUsedPercent;
	}
	
	public void setMemoryUsedPercent(double memoryUsedPercent) {
		this.memoryUsedPercent = memoryUsedPercent;
	}
	
	public double getMemoryFreePercent() {
		return memoryFreePercent;
	}
	
	public void setMemoryFreePercent(double memoryFreePercent) {
		this.memoryFreePercent = memoryFreePercent;
	}
	
	public long getMemoryBuffersCacheUsed() {
		return memoryBuffersCacheUsed;
	}
	
	public void setMemoryBuffersCacheUsed(long memoryBuffersCacheUsed) {
		this.memoryBuffersCacheUsed = memoryBuffersCacheUsed;
	}
	
	public long getMemoryBuffersCacheFree() {
		return memoryBuffersCacheFree;
	}
	
	public void setMemoryBuffersCacheFree(long memoryBuffersCacheFree) {
		this.memoryBuffersCacheFree = memoryBuffersCacheFree;
	}

	@Override
	public String toString() {
		return "machine";
	}	
}
