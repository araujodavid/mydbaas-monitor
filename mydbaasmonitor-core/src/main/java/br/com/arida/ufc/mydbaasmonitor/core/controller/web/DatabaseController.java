package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;
import java.util.Date;
import java.util.List;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;

/**
 * Class that manages the methods that the front-end databases accesses.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 20, 2013
 * Front-end: web/WEB-INF/jsp/database
 */

@Resource
public class DatabaseController extends AbstractController implements GenericController<Database> {

	private DatabaseRepository databaseRepository;
	private DBMSRepository dbmsRepository;
	
	public DatabaseController(Result result, Validator validator, DatabaseRepository databaseRepository, DBMSRepository dbmsRepository) {
		super(result, validator);
		this.databaseRepository = databaseRepository;
		this.dbmsRepository = dbmsRepository;
	}

	@Path("/databases")
	@Override
	public void redirect() {
		this.result.redirectTo(this).list();		
	}

	@Path("/databases/list")
	@Override
	public List<Database> list() {
		List<Database> databases = databaseRepository.all();
		//Information for pie chart
		int amountActive = 0;
		int amountNotActive = 0;
		for (Database database : databases) {
			if (database.getStatus() == true) {
				amountActive++;
			} else {
				amountNotActive++;
			}
		}
		this.result
		.include("amountActive", amountActive)
		.include("amountNotActive", amountNotActive);
		return databases;
	}

	@Path("/databases/new")
	@Override
	public void form() {
		//Includes the current date
		//List available Virtual Machines
		this.result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("availableDBMSs", dbmsRepository.all());		
	}
	
	public void form(DBMS dbms) {
		this.result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("availableDBMSs", dbmsRepository.all())
		.include("dbms", dbms);		
	}

	@Path("/databases/add")
	@Override
	public void add(final Database entity) {
		//Validations by vRaptor
		validator.checking(new Validations() {{
			that(!(entity.getDbms().getId() == 0), "DBMS", "database.dbms.empty");
			that(!(entity.getAlias() == null), "Alias", "database.alias.empty");
			that(!(entity.getName() == null), "Schema Name", "database.name.empty");
		} });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).form(entity.getDbms());
		
		databaseRepository.save(entity);
		result
		.include("notice", i18n("database.save.ok"))
		.redirectTo(DBMSController.class).view(entity.getDbms());
	}//add()

	@Path("/databases/edit/{entity.id}")
	@Override
	public Database edit(Database entity) {
		//List available Virtual Machines
		this.result
		.include("availableDBMSs", dbmsRepository.all());	
		return databaseRepository.find(entity.getId());
	}

	@Path("/databases/update")
	@Override
	public void update(final Database database) {
//		if (database.getStatus() == null) { 
//			database.setStatus(false); 
//		}
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(database.getDbms().getId() == 0), "DBMS", "database.dbms.empty");
			that(!(database.getAlias() == null), "Alias", "database.alias.empty");
			that(!(database.getName() == null), "Schema Name", "database.name.empty");		        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).edit(database);
		
		databaseRepository.update(database);
		result
		.include("notice", i18n("database.update.ok"))
		.redirectTo(this).view(database);
	} //update()

	@Path("/databases/view/{entity.id}")
	@Override
	public Database view(Database entity) {
		entity = databaseRepository.find(entity.getId());
		entity.setDbms(dbmsRepository.find(entity.getDbms().getId()));
		return entity;
	}

	@Override
	public void delete(Database entity) {
		// TODO Auto-generated method stub		
	}
}
