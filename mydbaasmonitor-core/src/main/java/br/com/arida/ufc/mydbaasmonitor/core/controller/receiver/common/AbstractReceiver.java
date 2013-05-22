package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Abstract class for new Receivers
 * @author David Araújo
 * @version 2.0
 * @since March 29, 2013
 */
public abstract class AbstractReceiver {

	protected DefaultStatus status;
	protected MetricRepository repository;
	
	/**
	 * Default constructor for Receiver
	 * @param status
	 */
	public AbstractReceiver(DefaultStatus status, MetricRepository repository) {
		this.status = status;
		this.repository = repository;
	}
}
