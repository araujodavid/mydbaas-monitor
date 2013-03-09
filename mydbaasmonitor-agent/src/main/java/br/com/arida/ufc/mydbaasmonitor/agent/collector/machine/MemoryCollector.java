package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.MemoryMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 5, 2013
 * 
 */

public class MemoryCollector extends AbstractMachineCollector<MemoryMetric>  {

    private static Long format(long value) {
        return new Long(value / 1024);
    }
	
	@Override
	public MemoryMetric loadMetric(Sigar sigar) throws SigarException {
		MemoryMetric memoryMetric = MemoryMetric.getInstance();
		Mem mem = sigar.getMem();
		Swap swap = sigar.getSwap();
		
		memoryMetric.setSwapUsed(format(swap.getUsed()));
		memoryMetric.setSwapFree(format(swap.getFree()));
		memoryMetric.setMemoryUsed(format(mem.getUsed()));
		memoryMetric.setMemoryFree(format(mem.getFree()));
		memoryMetric.setMemoryUsedPercent(mem.getUsedPercent());
		memoryMetric.setMemoryFreePercent(mem.getFreePercent());
		memoryMetric.setBuffersCacheUsed(format(mem.getActualUsed()));
		memoryMetric.setBuffersCacheFree(format(mem.getActualFree()));
		
		return memoryMetric;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
	
}
