package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.util.Utils.i18n;

import java.util.Date;
import java.util.List;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DataUtil;

/**
 * Class that manages the methods that the front-end databases accesses.
 * @author David Araújo
 * @version 1.0
 * @since March 20, 2013
 * Front-end: web/WEB-INF/jsp/database
 */

@Resource
public class DatabaseController extends AbstractController implements GenericController<Database> {
	
	private DatabaseRepository repository;

	public DatabaseController(Result result, Validator validator, DatabaseRepository repository) {
		super(result, validator);
		this.repository = repository;
	}

	@Override
	public void redirect() {
		// TODO Auto-generated method stub		
	}

	@Override
	public List<Database> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public void form(VirtualMachine virtualMachine) {
		this.result
		.include("current_date", DataUtil.converteDateParaString(new Date()))
		.include("virtualMachine", virtualMachine);		
	}

	@Path("/database/add")
	public void add(final Database entity, final String confirmPassword) {
		//Validations by vRaptor
		validator.checking(new Validations() {{
			that(!(entity.getVirtualMachine().getId() == 0), "Virtual Machine", "database.machine.empty");
			that(!(entity.getType().equals("select")), "Type", "database.type.empty");
			that(!(entity.getAlias() == null), "Alias", "database.alias.empty");
	        that(!(entity.getUser() == null), "Username", "database.username.empty");
	        that(!(entity.getPort() == null), "Port", "database.port.empty");
	        that(!(entity.getPassword() == null), "Password", "database.password.empty");
	        that(!(confirmPassword == null), "Confirm Password", "database.confirm.empty");
	        if (entity.getPassword() != null || confirmPassword != null) {
	        	that(entity.getPassword().equals(confirmPassword), "Password", "database.password.not.checked");
	        }	        	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).form(entity.getVirtualMachine());
		
		repository.save(entity);
		result
		.include("notice", i18n("database.save.ok"))
		.redirectTo(VirtualMachineController.class).view(entity.getVirtualMachine());
	}// add()

	@Override
	public Database edit(Database entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Database entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Database view(Database entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Database entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void add(Database entity) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void form() {
		// TODO Auto-generated method stub		
	}

}
