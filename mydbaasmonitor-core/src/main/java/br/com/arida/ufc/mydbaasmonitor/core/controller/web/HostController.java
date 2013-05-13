package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import java.util.List;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;

/**
 * Class that manages the methods that the front-end hosts accesses.
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 13, 2013
 * Front-end: web/WEB-INF/jsp/host
 */

@Resource
public class HostController extends AbstractController implements GenericController<Host> {

	public HostController(Result result, Validator validator) {
		super(result, validator);
	}

	@Override
	public void redirect() {
		// TODO Auto-generated method stub		
	}

	@Override
	public List<Host> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void form() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void add(Host entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Host edit(Host entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Host entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public Host view(Host entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Host entity) {
		// TODO Auto-generated method stub		
	}

}
