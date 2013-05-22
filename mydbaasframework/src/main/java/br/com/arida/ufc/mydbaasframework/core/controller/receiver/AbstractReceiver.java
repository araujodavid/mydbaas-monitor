package main.java.br.com.arida.ufc.mydbaasframework.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasframework.core.repository.MetricRepository;
import main.java.br.com.arida.ufc.mydbaasframework.core.repository.connection.AbstractDatabaseConnection;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Abstract class for new Receivers
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */
public abstract class AbstractReceiver<T extends MetricRepository<AbstractDatabaseConnection>> {

	protected DefaultStatus status;
	protected T repository;
	
	/**
	 * Default constructor for Receiver
	 * @param status
	 * @param repository
	 */
	public AbstractReceiver(DefaultStatus status, T repository) {
		this.status = status;
		this.repository = repository;
	}
}
