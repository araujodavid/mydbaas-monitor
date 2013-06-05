package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Class that manages the front-end to configure and send agent to resources.
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since June 3, 2013
 */

@Resource
public class AgentController {

	private Result result;
	
	public AgentController(Result result) {
		this.result = result;
	}
}
