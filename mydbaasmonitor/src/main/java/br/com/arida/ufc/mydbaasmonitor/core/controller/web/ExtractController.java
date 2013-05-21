package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Class that manages the methods to extract collected metrics.
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 16, 2013
 * Front-end: web/WEB-INF/jsp/extract
 */

@Resource
public class ExtractController {

	private Result result;
	
	public ExtractController(Result result) {
		this.result = result;
	}
}
