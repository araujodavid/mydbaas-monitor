package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.SOMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 5, 2013
 * 
 */

public class SOCollector extends AbstractMachineCollector<SOMetric>  {

	@Override
	public void loadMetric(Sigar sigar) throws SigarException {
		this.metric = SOMetric.getInstance();
		OperatingSystem sys = OperatingSystem.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		CpuInfo cpuInfo = sigar.getCpuInfoList()[0];
		
		this.metric.setOperatingSystem(sys.getDescription());
		this.metric.setKernelName(sys.getName());
		this.metric.setKernelVersion(sys.getVersion());
		this.metric.setArchitecture(sys.getArch());
		this.metric.setTotalMemory(mem.getTotal());
		this.metric.setTotalSwap(swap.getTotal());
		this.metric.setTotalCPUCores(cpuInfo.getTotalCores());
		this.metric.setTotalCPUSockets(cpuInfo.getTotalSockets());
		this.metric.setTotalCoresPerSocket(cpuInfo.getCoresPerSocket());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
}
