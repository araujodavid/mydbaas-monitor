package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import java.util.Date;
import java.util.List;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;

/**
 * Class that manages the methods that the front-end DBMS accesses.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 20, 2013
 * Front-end: web/WEB-INF/jsp/dBMS
 */

@Resource
public class DBMSController extends AbstractController implements GenericController<DBMS> {

	private DBMSRepository repository;
	private DatabaseRepository databaseRepository;
	private VirtualMachineRepository virtualMachineRepository;
	
	public DBMSController(Result result, Validator validator, DBMSRepository repository, DatabaseRepository databaseRepository, VirtualMachineRepository virtualMachineRepository) {
		super(result, validator);
		this.repository = repository;
		this.databaseRepository = databaseRepository;
		this.virtualMachineRepository = virtualMachineRepository;
	}

	@Path("/dbmss")
	@Override
	public void redirect() {
		this.result.redirectTo(this).list();		
	}

	@Path("/dbmss/list")
	@Override
	public List<DBMS> list() {
		List<DBMS> dbmsList = repository.all();
		//Information for pie chart
		int amountActive = 0;
		int amountNotActive = 0;
		int amountWithDB = 0;
		int amountWithoutDB = 0;
		for (DBMS dbms : dbmsList) {
			if (dbms.getStatus() == true) {
				amountActive++;
			} else {
				amountNotActive++;
			}
			dbms.setDatabases(databaseRepository.getDBMSDatabases(dbms.getId()));
			if (dbms.getDatabases().isEmpty()) {
				amountWithoutDB++;
			} else {
				amountWithDB++;
			}
		}
		this.result
		.include("amountActive", amountActive)
		.include("amountNotActive", amountNotActive)
		.include("amountWithDB", amountWithDB)
		.include("amountWithoutDB", amountWithoutDB);
		return dbmsList;
	}
	
	public void form(VirtualMachine virtualMachine) {
		this.result
		.include("current_date", DataUtil.converteDateParaString(new Date()))
		.include("availableVMs", virtualMachineRepository.all())
		.include("virtualMachine", virtualMachine);		
	}
	
	@Path("/dbmss/new")
	@Override
	public void form() {
		//Includes the current date
		//List available Virtual Machines
		this.result
		.include("current_date", DataUtil.converteDateParaString(new Date()))
		.include("availableVMs", virtualMachineRepository.all());	
	}

	@Path("/dbmss/add")
	public void add(final DBMS entity, final String confirmPassword) {
		//Validations by vRaptor
		validator.checking(new Validations() {{
			that(!(entity.getMachine().getId() == 0), "Virtual Machine", "dbms.machine.empty");
			that(!(entity.getType().equals("select")), "Type", "dbms.type.empty");
			that(!(entity.getAlias() == null), "Alias", "dbms.alias.empty");
	        that(!(entity.getUser() == null), "Username", "dbms.username.empty");
	        that(!(entity.getPort() == null), "Port", "dbms.port.empty");
	        that(!(entity.getPassword() == null), "Password", "dbms.password.empty");
	        that(!(confirmPassword == null), "Confirm Password", "dbms.confirm.empty");
	        if (entity.getPassword() != null || confirmPassword != null) {
	        	that(entity.getPassword().equals(confirmPassword), "Password", "dbms.password.not.checked");
	        }	        	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).form(entity.getMachine());
		
		repository.save(entity);
		result
		.include("notice", i18n("dbms.save.ok"))
		.redirectTo(VirtualMachineController.class).view(entity.getMachine());
	}//add()

	@Path("/dbmss/edit/{entity.id}")
	@Override
	public DBMS edit(DBMS entity) {
		//List available Virtual Machines
		this.result
		.include("availableVMs", virtualMachineRepository.all());	
		return repository.find(entity.getId());
	}
	
	@Path("/dbmss/update")
	public void update(final DBMS dbms, final String confirmPassword) {
		if (dbms.getStatus() == null) { 
			dbms.setStatus(false); 
		}
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(dbms.getMachine().getId() == 0), "Virtual Machine", "dbms.machine.empty");
			that(!(dbms.getType().equals("select")), "Type", "dbms.type.empty");
			that(!(dbms.getAlias() == null), "Alias", "dbms.alias.empty");
	        that(!(dbms.getUser() == null), "Username", "dbms.username.empty");
	        that(!(dbms.getPort() == null), "Port", "dbms.port.empty");
	        that(!(dbms.getPassword() == null), "Password", "dbms.password.empty");
	        that(!(confirmPassword == null), "Confirm Password", "dbms.confirm.empty");
	        if (dbms.getPassword() != null || confirmPassword != null) {
	        	that(dbms.getPassword().equals(confirmPassword), "Password", "dbms.password.not.checked");
	        }		        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).edit(dbms);
		
		repository.update(dbms);
		result
		.include("notice", i18n("dbms.update.ok"))
		.redirectTo(DBMSController.class).view(dbms);
	} //update()

	@Path("/dbmss/view/{entity.id}")
	@Override
	public DBMS view(DBMS entity) {
		entity = repository.find(entity.getId());
		entity.setMachine(virtualMachineRepository.find(entity.getMachine().getId()));
		entity.setDatabases(databaseRepository.getDBMSDatabases(entity.getId()));
		result.include("current_date", DataUtil.converteDateParaString(new Date()));
		return entity;
	}

	/**
	 * Override methods
	 */
	
	@Override
	public void delete(DBMS entity) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void add(DBMS entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void update(DBMS entity) {
		// TODO Auto-generated method stub		
	}
}
