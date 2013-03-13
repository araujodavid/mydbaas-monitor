package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common;

import java.util.TimerTask;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 8, 2013
 * 
 */

public abstract class AbstractMachineCollector<T extends AbstractMetric> extends TimerTask {

	protected T metric;
	protected int machine;
	
	/**
	 * Method that receives an instance of Sigar and makes the collection of a particular metric.
	 * @param sigar - an instance of Sigar API 
	 * @throws SigarException
	 */
	//Method to load the entity metric values
	public abstract void loadMetric(Sigar sigar) throws SigarException;	

}
