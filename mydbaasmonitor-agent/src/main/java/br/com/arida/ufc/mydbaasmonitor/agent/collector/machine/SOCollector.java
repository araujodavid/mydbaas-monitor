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
	public SOMetric loadMetric(Sigar sigar) throws SigarException {
		SOMetric soMetric = SOMetric.getInstance();
		OperatingSystem sys = OperatingSystem.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		CpuInfo cpuInfo = sigar.getCpuInfoList()[0];
		
		soMetric.setOperatingSystem(sys.getDescription());
		soMetric.setKernelName(sys.getName());
		soMetric.setKernelVersion(sys.getVersion());
		soMetric.setArchitecture(sys.getArch());
		soMetric.setTotalMemory(mem.getTotal());
		soMetric.setTotalSwap(swap.getTotal());
		soMetric.setTotalCPUCores(cpuInfo.getTotalCores());
		soMetric.setTotalCPUSockets(cpuInfo.getTotalSockets());
		soMetric.setTotalCoresPerSocket(cpuInfo.getCoresPerSocket());
		
		return soMetric;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	
}
