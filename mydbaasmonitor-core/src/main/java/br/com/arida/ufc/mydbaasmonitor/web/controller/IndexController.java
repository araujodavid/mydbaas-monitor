package main.java.br.com.arida.ufc.mydbaasmonitor.web.controller;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Class that manages the methods that the front-end index accesses.
 * @author David Araújo
 * Front-end: web/WEB-INF/jsp/index
 */

@Resource
public class IndexController {

	private final Result result;
	private final VirtualMachineRepository machineRepository;

	public IndexController(Result result, VirtualMachineRepository machineRepository) {
		this.result = result;
		this.machineRepository = machineRepository;
	}

	@Path("/")
	public void index() {		
		result.include("listResources", machineRepository.list(true));		
	}

}
