package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.util.Utils.i18n;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBaaSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DBaaSComparator;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DataUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

/**
 * Class that manages the methods that the front-end dbaas accesses.
 * @author David Araújo
 * @version 2.0
 * @since March 18, 2013
 * Front-end: web/WEB-INF/jsp/dBaaS
 */

@Resource
public class DBaaSController extends AbstractController implements GenericController<DBaaS> {

	private DBaaSRepository repository;
	
	public DBaaSController(Result result, Validator validator, DBaaSRepository repository) {
		super(result, validator);
		this.repository = repository;
	}	
	
	@Path("/dbaas")
	@Override
	public void redirect() {
		this.result.redirectTo(this).list();		
	}

	@Path("/dbaas/list")
	@Override
	public List<DBaaS> list() {		
		List<DBaaS> dBaaSList = repository.all();
		List<DBaaS> highlightsDBaaS = new ArrayList<DBaaS>();
		List<DBaaS> allDBaaS = new ArrayList<>(dBaaSList);
		
		//Sorts DBaaS by their amount of virtual machines registered
		Collections.sort(dBaaSList, new DBaaSComparator());
		Collections.reverse(dBaaSList);
		
		//Get the three main DBaaS
		if (dBaaSList.size() >= 3) {
			highlightsDBaaS.add(0, dBaaSList.get(0));
			highlightsDBaaS.add(1, dBaaSList.get(1));
			highlightsDBaaS.add(2, dBaaSList.get(2));
		} else if (dBaaSList.size() == 2) {
			highlightsDBaaS.add(0, dBaaSList.get(0));
			highlightsDBaaS.add(1, dBaaSList.get(1));
		} else {
			highlightsDBaaS.add(0, dBaaSList.get(0));
		}
		
		result
		.include("current_date", DataUtil.converteDateParaString(new Date()))
		.include("allDBaaS", allDBaaS)
		.include("highlightsDBaaS", highlightsDBaaS);
		return dBaaSList;
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

	@Path("/dbaas/edit/{entity.id}")
	@Override
	public DBaaS edit(DBaaS entity) {
		return repository.find(entity.getId());
	}

	@Path("/dbaas/update")
	@Override
	public void update(final DBaaS entity) {
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(entity.getAlias() == null), "Alias", "dbaas.alias.empty");
	        that(!(entity.getDescription() == null), "Description", "dbaas.description.empty");		        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(this).edit(entity);
		
		repository.update(entity);
		result
		.include("notice", i18n("dbaas.update.ok"))
		.redirectTo(this).view(entity);
	} //update()

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
