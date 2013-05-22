package main.java.br.com.arida.ufc.mydbaasframework.core.controller.api;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Abstract class for new PoolController
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class AbstractPoolController {

	protected Result result;
	protected DefaultStatus status;
	
	public AbstractPoolController(Result result, DefaultStatus status) {
		this.result = result;
		this.status = status;
	}
	
	public abstract void poolConnection();
	
	public abstract void poolMetric(String metricsType);
	
	public abstract void poolDBaaS();
	
	public abstract void poolHost();
	
	public abstract void poolVirtualMachine();
	
	public abstract void poolDBMS();
	
	public abstract void poolDatabase();
}
