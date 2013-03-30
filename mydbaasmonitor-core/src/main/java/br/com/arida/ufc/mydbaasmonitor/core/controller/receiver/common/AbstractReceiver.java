package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common;

import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Abstract class for new Receivers
 * @author David Araújo
 * @version 1.0
 * @since March 29, 2013
 */
public abstract class AbstractReceiver {

	protected DefaultStatus status;
	
	/**
	 * Default constructor for Receiver
	 * @param status
	 */
	public AbstractReceiver(DefaultStatus status) {
		this.status = status;
	}
}
