package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.util.Utils.i18n;

import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBaaSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DataUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

/**
 * Class that manages the methods that the front-end dbaas accesses.
 * @author David Araújo
 * @version 1.0
 * @since March 18, 2013
 * Front-end: web/WEB-INF/jsp/dbaas
 */

@Resource
public class DBaaSController implements GenericController<DBaaS> {

	private DBaaSRepository repository;
	private Result result;
	private Validator validator;
	
	public DBaaSController(DBaaSRepository repository, Result result, Validator validator) {
		this.repository = repository;
		this.result = result;
		this.validator = validator;
	}
	
	@Path("/dbaas")
	@Override
	public void redirect() {
		this.result.redirectTo(this).list();		
	}

	@Path("/dbaas/list")
	@Override
	public List<DBaaS> list() {
		this.result.include("current_date", DataUtil.converteDateParaString(new Date()));
		return repository.all();
	}

	@Path("/dbaas/new")
	@Override
	public void form() {
		//Includes the current date
		this.result.include("current_date", DataUtil.converteDateParaString(new Date()));		
	}

	@Path("/dbaas/add")
	@Override
	public void add(final DBaaS entity) {
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(entity.getAlias() == null), "Alias", "dbaas.alias.empty");
	        that(!(entity.getDescription() == null), "Description", "dbaas.description.empty");	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).form();

		repository.save(entity);
		result
		.include("notice", i18n("dbaas.save.ok"))
		.redirectTo(this).list();	
	}

	@Path("/dbaas/edit/{dbaas.id}")
	@Override
	public DBaaS edit(DBaaS entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Path("/dbaas/update")
	@Override
	public void update(DBaaS entity) {
		// TODO Auto-generated method stub		
	}

	@Path("/dbaas/view/{dbaas.id}")
	@Override
	public DBaaS view(DBaaS entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Path("/dbaas/delete/{dbaas.id}")
	@Override
	public void delete(DBaaS entity) {
		// TODO Auto-generated method stub		
	}

}
