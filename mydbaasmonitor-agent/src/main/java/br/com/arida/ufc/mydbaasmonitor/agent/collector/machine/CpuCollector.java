package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import org.hyperic.sigar.Sigar;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common.AbstractMachineCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.CpuMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 5, 2013
 * 
 */

public class CpuCollector extends AbstractMachineCollector<CpuMetric> {

	@Override
	public CpuMetric loadMetric(Sigar sigar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}	

}
