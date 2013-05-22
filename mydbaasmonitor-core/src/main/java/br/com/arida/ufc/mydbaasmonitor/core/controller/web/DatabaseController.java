package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import java.util.List;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;

/**
 * Class that manages the methods that the front-end databases accesses.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since March 20, 2013
 * Front-end: web/WEB-INF/jsp/database
 */

@Resource
public class DatabaseController extends AbstractController implements GenericController<Database> {

	public DatabaseController(Result result, Validator validator) {
		super(result, validator);
		// TODO Auto-generated constructor stub
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
