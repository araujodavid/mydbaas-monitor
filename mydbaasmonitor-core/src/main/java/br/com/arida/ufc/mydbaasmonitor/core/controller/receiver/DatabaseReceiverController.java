package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Class that handles requests sent by the monitoring agents about database.
 * @author Daivd Araújo
 * @version 1.0
 * @since March 10, 2013
 */

@Resource
@Path("/database")
public class DatabaseReceiverController extends AbstractReceiver {

	/**
	 * Constructor
	 * @param status
	 */
	public DatabaseReceiverController(DefaultStatus status) {
		super(status);
	}

	
}
