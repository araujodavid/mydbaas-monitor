package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.common.AbstractMetric;
import org.apache.http.NameValuePair;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * 
 * @author Daivd Araújo
 * @version 3.0
 * @since March 8, 2013
 * 
 */
public abstract class AbstractMachineCollector<T extends AbstractMetric> extends TimerTask {

	protected T metric;
	protected int machine;
	
	/**
	 * Method that receives an instance of Sigar and makes the collection of a particular metric.
	 * Method to load the entity metric values.
	 * @param sigar - an instance of Sigar API 
	 * @throws SigarException
	 */
	public abstract void loadMetric(Sigar sigar) throws SigarException;
	
	/**
	 * Method to load the request parameters of the metric.
	 * @param recordDate - datetime when the metric was collected
	 * @return a list of parameters and values ​​for the HTTP request
	 */
	public List<NameValuePair> loadRequestParams(Date recordDate) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			
		return parameters;		
	}

}
