package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

/**
 * Class that manages the methods that the front-end index accesses.
 * @author David Araújo
 * Front-end: web/WEB-INF/jsp/index
 */

@Resource
public class IndexController extends AbstractController {
	
	private final VirtualMachineRepository machineRepository;

	public IndexController(Result result, Validator validator, VirtualMachineRepository machineRepository) {
		super(result, validator);
		this.machineRepository = machineRepository;
	}	

	@Path("/")
	public void index() {		
		result.include("listResources", machineRepository.list(true));		
	}

}
