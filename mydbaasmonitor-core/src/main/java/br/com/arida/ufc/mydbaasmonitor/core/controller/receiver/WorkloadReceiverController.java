package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import java.lang.reflect.InvocationTargetException;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.WorkloadStatus;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

@Resource
@Path("/workload")
public class WorkloadReceiverController extends AbstractReceiver {

	public WorkloadReceiverController(DefaultStatus status, MetricRepository repository) {
		super(status, repository);
	}
	
	@Post("/workloadstatus")
	public void workloadStatus(WorkloadStatus metric, int database, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, 0, database)) {
				status.accepted();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}

