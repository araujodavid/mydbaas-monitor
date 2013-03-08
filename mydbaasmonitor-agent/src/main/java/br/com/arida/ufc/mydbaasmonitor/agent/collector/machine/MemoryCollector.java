package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import org.hyperic.sigar.Sigar;

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

	@Override
	public MemoryMetric loadMetric(Sigar sigar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
