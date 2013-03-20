package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;

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

	@Override
	public void form() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void add(Database entity) {
		// TODO Auto-generated method stub		
	}

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

}
