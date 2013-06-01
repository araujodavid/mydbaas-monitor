package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;
import java.util.Map;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.StarterRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.ConnectionPool;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;

/**
 * Class that manages the startup methods of the front-end.
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 26, 2013 
 * Front-end: web/WEB-INF/jsp/starter
 */

@Resource
public class StarterController {
	
	private Result result;
	private Validator validator;
	private StarterRepository starterRepository;

	public StarterController(Result result, Validator validator, StarterRepository starterRepository) {
		this.result = result;
		this.validator = validator;
		this.starterRepository = starterRepository;
	}
	
	@Path("/")
	public void index() {		
		this.result.redirectTo(DBaaSController.class).list();	
	}
	
	@Path("/starter")
	public void starter() {
		if ((ConnectionPool.getInstance().getSchema() != null) &&
		    (ConnectionPool.getInstance().getUsername() != null) &&
		    (ConnectionPool.getInstance().getConnection() != null)) {
			result.include("tables", starterRepository.verifyTables())
			      .include("pool", ConnectionPool.getInstance())
				  .include("connectionOK", true);
		} else {
			result.include("tables", starterRepository.verifyTables())
			  .include("pool", ConnectionPool.getInstance())
			  .include("connectionOK", false);
		}		
	}
	
	@Path("/starter/database")
	public void starterDatabase(final String schema, final String port, final String username, final String password) {
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(schema == null), "Schema", "pool.schema.empty");
			that(!(port == null), "Port", "pool.port.empty");
			if (port != null && !isDigit(port)) {
				validator.add(new ValidationMessage("The value reported for port is not numeric.", "Port"));
			}
			that(!(username == null), "Username", "pool.username.empty");
			that(!(password == null), "Password", "pool.password.empty");	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).starter();
		
		ConnectionPool.getInstance().setConfig(schema, Integer.parseInt(port), username, password);
		if (ConnectionPool.getInstance().getConnection() != null) {
			result
			.include("notice", i18n("connection.ok"))
			.redirectTo(this).starter();
		} else {
			result
			.include("erro", i18n("connection.error"))
			.redirectTo(this).starter();
		}
	}
	
	@Path("/starter/tables")
	public void starterResourceTables() {
		Map<String, Boolean> tables = starterRepository.verifyTables();
		
		boolean ok = true;
		for (Boolean value : tables.values()) {
			if (value == false) {
				ok = false;
			}
		}
		
		if (ok == true) {
			result
			.include("erro", i18n("tables.already.exist"))
			.redirectTo(this).starter();
		} else {
			starterRepository.createResourceTable(StarterRepository.DBaaS_Table);
			starterRepository.createResourceTable(StarterRepository.Host_Table);
			starterRepository.createResourceTable(StarterRepository.VirtualMachine_Table);
			starterRepository.createResourceTable(StarterRepository.DBMS_Table);
			starterRepository.createResourceTable(StarterRepository.Database_Table);
			
			result
			.include("notice", i18n("tables.save.ok"))
			.redirectTo(this).starter();
		}
	}
	
	private boolean isDigit(String s) {  
	    return s.matches("[0-9]*");  
	}	
}
