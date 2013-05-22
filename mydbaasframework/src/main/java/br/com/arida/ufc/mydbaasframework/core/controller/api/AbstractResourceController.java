package main.java.br.com.arida.ufc.mydbaasframework.core.controller.api;

import br.com.caelum.vraptor.Result;

/**
 * Abstract class for new ResourceController
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class AbstractResourceController {

	protected Result result;
	
	public AbstractResourceController(Result result) {
		this.result = result;
	}
	
	public abstract void getHosts(int identifier);
	
	public abstract void getMachines(int identifier, String ownerType);
	
	public abstract void getDBMSs(int identifier, String ownerType);
	
	public abstract void getDatabases(int identifier);
	
	public abstract void getResourceByID(int resourceId, String resourceType);
}
