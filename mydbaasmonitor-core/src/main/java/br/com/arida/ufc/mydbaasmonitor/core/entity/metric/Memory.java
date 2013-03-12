package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.common.AbstractMetric;

public class Memory extends AbstractMetric {
	
	private long swapUsed;
	private long swapFree;
	private long memoryUsed;
	private long memoryFree;
	private double memoryUsedPercent;
	private double memoryFreePercent;
	private long buffersCacheUsed;
	private long buffersCacheFree;
	
	public long getSwapUsed() {
		return swapUsed;
	}
	
	public void setSwapUsed(long swapUsed) {
		this.swapUsed = swapUsed;
	}
	
	public long getSwapFree() {
		return swapFree;
	}
	
	public void setSwapFree(long swapFree) {
		this.swapFree = swapFree;
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
	
	public long getBuffersCacheUsed() {
		return buffersCacheUsed;
	}
	
	public void setBuffersCacheUsed(long buffersCacheUsed) {
		this.buffersCacheUsed = buffersCacheUsed;
	}
	
	public long getBuffersCacheFree() {
		return buffersCacheFree;
	}
	
	public void setBuffersCacheFree(long buffersCacheFree) {
		this.buffersCacheFree = buffersCacheFree;
	}
}
