package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.StarterRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

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
	private StarterRepository starterRepository;

	public StarterController(Result result, StarterRepository starterRepository) {
		this.result = result;
		this.starterRepository = starterRepository;
	}
	
	@Path("/")
	public void index() {		
		this.result.redirectTo(DBaaSController.class).list();	
	}
	
	@Path("/starter")
	public void starter() {		
		//TODO	
	}
	
	@Path("/starter/database")
	public void starterDatabase() {
		//TODO
	}
	
	@Path("/starter/tables")
	public void starterResourceTables() {
		//TODO
	}
	
}
