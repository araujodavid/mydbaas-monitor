package main.java.br.com.arida.ufc.mydbaasmonitor.web.controller;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Validator;

/**
 * @author David Araújo
 * 
 * This controller is responsible for receiving the data sent by the monitoring agents via HTTP requests.
 *
 */

@Resource
public class ReceiverController {
	
	private Validator validator;
	
	public ReceiverController(Validator validator){
		this.validator = validator;		
	}
	
	//Webservice to receive information about virtual machine resources
	@Post("")
	public void aboutMachine(){
		
	}
	
	//Webservice to receive resource information database.
	@Post("")
	public void aboutDatabase(){
		
	}

}
