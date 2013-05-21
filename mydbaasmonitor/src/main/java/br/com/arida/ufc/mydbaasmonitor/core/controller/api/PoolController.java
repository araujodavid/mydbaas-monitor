package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractDatabaseMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBaaSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.HostRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import org.reflections.Reflections;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.DefaultStatus;
import br.com.caelum.vraptor.view.Results;

/**
 * Class that handles requests sent by the API module.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013 
 */

@Resource
@Path("/pool")
public class PoolController {
	
	private Result result;
	private DefaultStatus status;
	private DBaaSRepository dBaaSRepository;
	private VirtualMachineRepository machineRepository;
	private DBMSRepository dbmsRepository;
	private HostRepository hostRepository;
	private DatabaseRepository databaseRepository;
	
	public PoolController(Result result, DefaultStatus status, DBaaSRepository dBaaSRepository, VirtualMachineRepository machineRepository, DBMSRepository dbmsRepository, HostRepository hostRepository, DatabaseRepository databaseRepository) {
		this.result = result;
		this.status = status;
		this.dBaaSRepository = dBaaSRepository;
		this.machineRepository = machineRepository;
		this.dbmsRepository = dbmsRepository;
		this.hostRepository = hostRepository;
		this.databaseRepository = databaseRepository;
	}
	
	@Post("/connection")
	public void poolConnection() {
		status.ok();
	}

	/**
	 * Method that receives a given type of resource and returns the possible metrics
	 * @param metricsType - e.g.: database, machine, host
	 * @return a JSON of metrics name
	 */
	@Post("/metrics")
	public void poolMetric(String metricsType) {
		Reflections reflections = new Reflections("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric."+metricsType);
		List<String> metricsName = new ArrayList<String>();
		if (metricsType.equals("database")) {			
		    Set<Class<? extends AbstractDatabaseMetric>> classes = reflections.getSubTypesOf(AbstractDatabaseMetric.class);
		    for (Class<? extends AbstractDatabaseMetric> clazz : classes) {
				metricsName.add(clazz.getSimpleName());
			}
		} else {
		    Set<Class<? extends AbstractMetric>> classes = reflections.getSubTypesOf(AbstractMetric.class);
		    for (Class<? extends AbstractMetric> clazz : classes) {
				metricsName.add(clazz.getSimpleName());
			}
		}
		result
		.use(Results.json())
		.withoutRoot()
		.from(metricsName)
		.serialize();
	}
	
	/**
	 * Method to query the list of DBaaS
	 * @return a JSON of DBaaS
	 */
	@Post("/dbaas")
	public void poolDBaaS() {
		List<DBaaS> pool = this.dBaaSRepository.all();
		result
		.use(Results.json())
		.from(pool, "pool")
		.serialize();
	}
	
	/**
	 * Method to query the list of Hosts
	 * @return a JSON of Hosts
	 */
	@Post("/hosts")
	public void poolHost() {		
		List<Host> pool = this.hostRepository.all();
		result
		.use(Results.json())
		.from(pool, "pool")
		.include("environment")
		.serialize();
	}
	
	/**
	 * Method to query the list of Virtual Machines
	 * @return a JSON of virtual machines
	 */
	@Post("/machines")
	public void poolVirtualMachine() {
		List<VirtualMachine> pool = this.machineRepository.all();
		result
		.use(Results.json())
		.from(pool, "pool")
		.include("machine")
		.include("environment")
		.serialize();
	}
	
	/**
	 * Method to query the list of DBMSs
	 * @return a JSON of DBMSs
	 */
	@Post("/dbmss")
	public void poolDBMS() {
		List<DBMS> pool = this.dbmsRepository.all();
		result
		.use(Results.json())
		.from(pool, "pool")
		.include("machine")
		.serialize();
	}
	
	/**
	 * Method to query the list of Databases
	 * @return a JSON of databases
	 */
	@Post("/databases")
	public void poolDatabase() {
		List<Database> pool = this.databaseRepository.all();
		result
		.use(Results.json())
		.from(pool, "pool")
		.include("dbms")
		.serialize();
	}
	
}
