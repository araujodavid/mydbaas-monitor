package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ActiveConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.DiskUtilization;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.InformationData;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.InformationTable;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.NetworkTraffic;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ProcessStatus;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.Size;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementDCL;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementDDL;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementDML;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.StatementTCL;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Class that handles requests sent by the monitoring agents about DBMSs and Databases.
 * @author Daivd Araújo
 * @version 3.0
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
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, database)) {
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
	
	@Post("/size")
	public void size(Size metric, int dbms, int database, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, database)) {
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
	
	@Post("/networktraffic")
	public void networkTraffic(NetworkTraffic metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/processstatus")
	public void processStatus(ProcessStatus metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/informationdata")
	public void informationData(InformationData metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/informationtable")
	public void informationTable(List<InformationTable> metric, int database, String recordDate) {
		for (InformationTable informationTable : metric) {
			try {
				if (repository.saveMetric(informationTable, recordDate, 0, 0, 0, database)) {
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
	
	@Post("/statementdml")
	public void statementDML(StatementDML metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/statementtcl")
	public void statementTCL(StatementTCL metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/statementddl")
	public void statementDDL(StatementDDL metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/statementdcl")
	public void statementDCL(StatementDCL metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
	
	@Post("/diskutilization")
	public void diskUtilization(DiskUtilization metric, int dbms, String recordDate) {
		try {
			if (repository.saveMetric(metric, recordDate, 0, 0, dbms, 0)) {
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
