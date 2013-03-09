package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

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
	public CpuMetric loadMetric(Sigar sigar) throws SigarException {
		CpuMetric cpuMetric = CpuMetric.getInstance();
		CpuPerc cpuPerc = sigar.getCpuPerc();
		
		cpuMetric.setCpuUser(cpuPerc.getUser());
		cpuMetric.setCpuSystem(cpuPerc.getSys());
		cpuMetric.setCpuIdle(cpuPerc.getIdle());
		cpuMetric.setCpuNice(cpuPerc.getNice());
		cpuMetric.setCpuWait(cpuPerc.getWait());
		cpuMetric.setCpuCombined(cpuPerc.getCombined());
		
		return cpuMetric;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub		
	}	

}
