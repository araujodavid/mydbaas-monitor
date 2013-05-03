package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.api;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Class that handles requests sent by the API module.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013 
 */

@Resource
@Path("/pool")
public class PoolController {
	
	private Result result;
	
	public PoolController(Result result) {
		this.result = result;
	}

	/**
	 * Method that receives a given type of resource and returns the possible metrics
	 * @param metricsType - e.g.: database, machine, host
	 * @return a JSON of metrics name
	 */
	@Post("/metrics")
	public void poolMetric(String metricsType) {
		//TODO
	}
	
	/**
	 * Method to query the list of Hosts
	 * @return a JSON of Hosts
	 */
	@Post("/hosts")
	public void poolHost() {
		//TODO
	}
	
	/**
	 * Method to query the list of Virtual Machines
	 * @return a JSON of virtual machines
	 */
	@Post("/machines")
	public void poolVirtualMachine() {
		//TODO
	}
	
	/**
	 * Method to query the list of DBMSs
	 * @return a JSON of DBMSs
	 */
	@Post("/dbmss")
	public void poolDBMS() {
		//TODO
	}
	
	/**
	 * Method to query the list of Databases
	 * @return a JSON of databases
	 */
	@Post("/databases")
	public void poolDatabase() {
		//TODO
	}
	
}
