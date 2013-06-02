package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBaaSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.HostRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DBaaSComparator;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

/**
 * Class that manages the methods that the front-end dbaas accesses.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 18, 2013
 * Front-end: web/WEB-INF/jsp/dBaaS
 */

@Resource
public class DBaaSController extends AbstractController implements GenericController<DBaaS> {

	private DBaaSRepository repository;
	private HostRepository hostRepository;
	private VirtualMachineRepository virtualMachineRepository;
	private DBMSRepository dbmsRepository;
	private DatabaseRepository databaseRepository;
	
	public DBaaSController(Result result, Validator validator, DBaaSRepository repository, HostRepository hostRepository, VirtualMachineRepository virtualMachineRepository, DBMSRepository dbmsRepository, DatabaseRepository databaseRepository) {
		super(result, validator);
		this.repository = repository;
		this.hostRepository = hostRepository;
		this.virtualMachineRepository = virtualMachineRepository;
		this.dbmsRepository = dbmsRepository;
		this.databaseRepository = databaseRepository;
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
		for (DBaaS dBaaS : dBaaSList) {
			dBaaS.setHosts(hostRepository.getDBaaSHosts(dBaaS.getId()));
			dBaaS.setMachines(virtualMachineRepository.getDBaaSMachines(dBaaS.getId()));
		}
		
		//Get the DBMSs and Databases of the mains DBaaS
		for (DBaaS dBaaS : dBaaSList) {
			dBaaS.setDbmss(new ArrayList<DBMS>());
			dBaaS.setDatabases(new ArrayList<Database>());
			for (VirtualMachine virtualMachine : dBaaS.getMachines()) {
				dBaaS.getDbmss().addAll(dbmsRepository.getMachineDBMSs(virtualMachine.getId()));
				for (DBMS dbms : dBaaS.getDbmss()) {
					dBaaS.getDatabases().addAll(databaseRepository.getDBMSDatabases(dbms.getId()));
				}
			}			
		}
		
		List<DBaaS> highlightsDBaaS = new ArrayList<DBaaS>();		
		
		//Sorts DBaaS by their amount of virtual machines registered
		Collections.sort(dBaaSList, new DBaaSComparator());
		Collections.reverse(dBaaSList);
		
		List<DBaaS> restDBaaS = new ArrayList<>(dBaaSList);
		
		//Get the three main DBaaS
		if (!dBaaSList.isEmpty()) {
			if (dBaaSList.size() >= 3) {
				DBaaS dBaaS;
				dBaaS = dBaaSList.get(0);
				highlightsDBaaS.add(0, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
				
				dBaaS = dBaaSList.get(1);
				highlightsDBaaS.add(1, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
				
				dBaaS = dBaaSList.get(2);
				highlightsDBaaS.add(2, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
			} else if (dBaaSList.size() == 2) {
				DBaaS dBaaS;
				dBaaS = dBaaSList.get(0);
				highlightsDBaaS.add(0, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
				
				dBaaS = dBaaSList.get(1);
				highlightsDBaaS.add(1, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
			} else {
				DBaaS dBaaS = dBaaSList.get(0);
				highlightsDBaaS.add(0, dBaaS);
				if (!dBaaS.getHosts().isEmpty() || !dBaaS.getMachines().isEmpty()) {
					restDBaaS.remove(dBaaS);
				}
			}
		}		
		
		result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("restDBaaS", restDBaaS)
		.include("highlightsDBaaS", highlightsDBaaS);
		return dBaaSList;
	}

	@Path("/dbaas/new")
	@Override
	public void form() {
		//Includes the current date
		this.result.include("current_date", DataUtil.convertDateToStringUI(new Date()));		
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

	@Path("/dbaas/view/{entity.id}")
	@Override
	public DBaaS view(DBaaS entity) {
		entity = repository.find(entity.getId());
		entity.setHosts(hostRepository.getDBaaSHosts(entity.getId()));
		entity.setMachines(virtualMachineRepository.getDBaaSMachines(entity.getId()));
		//Get the DBMSs and Databases of the DBaaS
		entity.setDbmss(new ArrayList<DBMS>());
		entity.setDatabases(new ArrayList<Database>());
		for (VirtualMachine virtualMachine : entity.getMachines()) {
			entity.getDbmss().addAll(dbmsRepository.getMachineDBMSs(virtualMachine.getId()));
			for (DBMS dbms : entity.getDbmss()) {
				entity.getDatabases().addAll(databaseRepository.getDBMSDatabases(dbms.getId()));
			}
		}			
		result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("availableHosts", hostRepository.all());
		return entity;
	}

	@Path("/dbaas/delete/{dbaas.id}")
	@Override
	public void delete(DBaaS entity) {
		// TODO Auto-generated method stub		
	}

}
