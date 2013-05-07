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
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;

/**
 * Class that manages the methods that the front-end DBMS accesses.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 20, 2013
 * Front-end: web/WEB-INF/jsp/dbms
 */

@Resource
public class DBMSController extends AbstractController implements GenericController<DBMS> {

	private DBMSRepository repository;
	
	public DBMSController(Result result, Validator validator, DBMSRepository repository) {
		super(result, validator);
		this.repository = repository;
	}

	@Path("/dbms")
	@Override
	public void redirect() {
		// TODO Auto-generated method stub
		
	}

	@Path("/dbms/list")
	@Override
	public List<DBMS> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void form(VirtualMachine virtualMachine) {
		this.result
		.include("current_date", DataUtil.converteDateParaString(new Date()))
		.include("virtualMachine", virtualMachine);		
	}

	@Override
	public void form() {
		// TODO Auto-generated method stub
		
	}
	
	@Path("/dbms/add")
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

	@Override
	public void add(DBMS entity) {
		// TODO Auto-generated method stub
		
	}

	@Path("/dbms/edit/{entity.id}")
	@Override
	public DBMS edit(DBMS entity) {
		return repository.find(entity.getId());
	}
	
	@Path("/dbms/update")
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
		.include("notice", i18n("database.update.ok"))
		.redirectTo(VirtualMachineController.class).view(dbms.getMachine());
	} //update()

	@Override
	public void update(DBMS entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DBMS view(DBMS entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DBMS entity) {
		// TODO Auto-generated method stub
		
	}

}
