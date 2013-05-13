package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.api;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.HostRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

/**
 * Class that handles requests sent by the API module.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 9, 2013 
 */

@Resource
@Path("/resource")
public class ResourceController {
	
	private Result result;
	private HostRepository hostRepository;
	private DBMSRepository dbmsRepository;
	private DatabaseRepository databaseRepository;
	private VirtualMachineRepository virtualMachineRepository;
		
	public ResourceController(Result result, HostRepository hostRepository, DBMSRepository dbmsRepository, DatabaseRepository databaseRepository, VirtualMachineRepository virtualMachineRepository) {
		this.result = result;
		this.hostRepository = hostRepository;
		this.dbmsRepository = dbmsRepository;
		this.databaseRepository = databaseRepository;
		this.virtualMachineRepository = virtualMachineRepository;
	}

	@Post("/hosts")
	public void getHosts(int identifier) {
		List<Host> hosts = this.hostRepository.getDBaaSHosts(identifier);
		result
		.use(Results.json())
		.withoutRoot()
		.from(hosts)
		.include("environment")
		.serialize();
	}
	
	@Post("/machines")
	public void getMachines(int identifier, String ownerType) {
		List<VirtualMachine> machines = null;
		switch (ownerType) {
		case "dbaas":
			machines = this.virtualMachineRepository.getDBaaSMachines(identifier);
			break;
		case "host":
			machines = this.virtualMachineRepository.getHostMachines(identifier);
			break;
		}		
		result
		.use(Results.json())
		.withoutRoot()
		.from(machines)
		.include("machine")
		.include("environment")
		.serialize();
	}
	
	@Post("/dbmss")
	public void getDBMSs(int identifier, String ownerType) {
		List<DBMS> dbmss = this.dbmsRepository.getMachineDBMSs(identifier);
		result
		.use(Results.json())
		.withoutRoot()
		.from(dbmss)
		.include("machine")
		.serialize();
	}
	
	@Post("/databases")
	public void getDatabases(int identifier) {
		List<Database> databases = this.databaseRepository.getDBMSDatabases(identifier);
		result
		.use(Results.json())
		.withoutRoot()
		.from(databases)
		.include("dbms")
		.serialize();
	}
	
	@Post("/find")
	public void getResourceByID(int resourceId, String resourceType) {
		//TODO
	}
	
}
