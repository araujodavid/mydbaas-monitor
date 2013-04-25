package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import java.lang.reflect.InvocationTargetException;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ActiveConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Class that handles requests sent by the monitoring agents about DBMSs and Databases.
 * @author Daivd Araújo
 * @version 2.0
 * @since March 10, 2013
 */

@Resource
@Path("/storage")
public class StorageReceiverController extends AbstractReceiver {

	/**
	 * Constructor
	 * @param status
	 * @param metricRepository
	 */
	public StorageReceiverController(DefaultStatus status, MetricRepository repository) {
		super(status, repository);
	}

	@Post("/activeconnections")
	public void activeConnections(ActiveConnection metric, int dbms, int database, String recordDate) {
		try {
			if (repository.saveMetric(metric, 0, recordDate, dbms, database)) {
				status.accepted();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
