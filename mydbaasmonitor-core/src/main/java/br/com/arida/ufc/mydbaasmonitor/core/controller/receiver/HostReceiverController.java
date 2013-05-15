package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Class that handles requests sent by the monitoring agents about host.
 * @author Daivd Araújo
 * @version 1.0
 * @since May 14, 2013 
 */

@Resource
@Path("/host")
public class HostReceiverController extends AbstractReceiver {

	public HostReceiverController(DefaultStatus status, MetricRepository repository) {
		super(status, repository);
	}

}
