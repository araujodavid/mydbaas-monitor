package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import java.util.Date;
import java.util.List;
import static main.java.br.com.arida.ufc.mydbaasmonitor.util.Utils.i18n;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DataUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;

/**
 * Class that manages the methods that the front-end virtual machine accesses.
 * @author David Araújo
 * @version 3.0
 * @since March 18, 2013
 * Front-end: web/WEB-INF/jsp/virtualMachine
 */

@Resource
public class VirtualMachineController {
	
	private VirtualMachineRepository repository;
	private Result result;
	private Validator validator;
	
	public VirtualMachineController(VirtualMachineRepository repository, Result result, Validator validator){
		this.repository = repository;
		this.result = result;
		this.validator = validator;
	}
	
	@Path("/vms")
	public void redirect(){		
		this.result.redirectTo(this).list();
	}
	
	@Path("/vms/list")
	public List<VirtualMachine> list(){
		return repository.all();
	}
	
	/**
	 * Method that is triggered when the page of registered resource is called.
	 * Send all data necessary to render the registration page.
	 * Url: /vms/new
	 * Front-end: /jsp/virtualMachine/form.jsp 
	 */
	@Path("/vms/new")
	public void form(){
		//Includes the current date
		this.result.include("current_date", DataUtil.converteDateParaString(new Date()));
	}
	
	/**
	 * Method that takes an object virtual machine to perform validations and sends to the ResourceManager to register.
	 * @param virtualMachine - object sent by the form
	 * @param confirmPassword - password confirmation for validation
	 */
	@Path("/vms/add")
	public void add(final VirtualMachine virtualMachine, final String confirmPassword){
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(virtualMachine.getAlias() == null), "Alias", "machine.alias.empty");
	        that(!(virtualMachine.getHost() == null), "Host", "machine.host.empty");
	        that(!(virtualMachine.getUser() == null), "Username", "machine.username.empty");
	        that(!(virtualMachine.getPort() == null), "Port", "machine.port.empty");
	        if (virtualMachine.getPassword() != null || confirmPassword != null) {
	        	that(virtualMachine.getPassword().equals(confirmPassword), "Password", "machine.password.not.checked");
			}	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(VirtualMachineController.class).form();
		
		//If everything is ok the object is sent to the ResourceManager and the user is redirected to the page that lists the registered resources
		repository.save(virtualMachine);
		result
		.include("notice", i18n("machine.save.ok"))
		.redirectTo(this).list();
	}
	
	/**
	 * Method that receives an object with only the id, and makes the search in the database to return the full object for rendering the web page.
	 * @param virtualMachine - object with only the id
	 * @return a virtual machine object to be to be edited
	 */
	@Path("/vms/edit/{virtualMachine.id}")
	public VirtualMachine edit(VirtualMachine virtualMachine){
		return repository.find(virtualMachine.getId());		
	}
	
	/**
	 * Method that takes an object virtual machine to perform validations and sends to the ResourceManager to update.
	 * @param virtualMachine - object sent by the form
	 * @param confirmPassword - password confirmation for validation
	 */
	@Path("/vms/update")
	public void update(final VirtualMachine virtualMachine){
		if (virtualMachine.getStatus() == null) { 
			virtualMachine.setStatus(false); 
		}
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(virtualMachine.getAlias() == null), "Alias", "machine.alias.empty");
	        that(!(virtualMachine.getHost() == null), "Host", "machine.host.empty");
	        that(!(virtualMachine.getUser() == null), "Username", "machine.username.empty");
	        that(!(virtualMachine.getPort() == null), "Port", "machine.port.empty");	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(VirtualMachineController.class).edit(virtualMachine);
		
		//If everything is ok the object is sent to the ResourceManager and the user is redirected to the page that lists the registered resources
		repository.update(virtualMachine);
		result
		.include("notice", i18n("machine.update.ok"))
		.redirectTo(this).view(virtualMachine);
	} //update()
	
	@Path("/vms/machine/password")
	public void passwordUpdate(final VirtualMachine virtualMachine, final String confirmPassword){
		//Validations by vRaptor
			validator.checking(new Validations() { {
				if (virtualMachine.getPassword() != null || confirmPassword != null) {
		        	that(virtualMachine.getPassword().equals(confirmPassword), "Password", "machine.password.not.checked");
				} else {
					validator.add(new ValidationMessage("Please enter a password. It can't be empty.", "Password"));
				}    
		    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(VirtualMachineController.class).edit(virtualMachine);
		
		//If everything is ok the object is sent to the ResourceManager and the user is redirected to the page that lists the registered resources
		repository.updatePassword(virtualMachine);
		result
		.include("notice", i18n("machine.update.password.ok"))
		.redirectTo(this).view(virtualMachine);
	}
	
	/**
	 * Method that receives an object with only the id, and makes the search in the database to return the full object for rendering the web page.
	 * @param virtualMachine - object with only the id
	 * @return a virtual machine object to be viewed
	 */
	@Path("/vms/view/{virtualMachine.id}")
	public VirtualMachine view(VirtualMachine virtualMachine){
		result.include("listResources", repository.all());
		return repository.find(virtualMachine.getId());		
	}
	
	@Path("/vms/delete/{virtualMachine.id}")
	public void delete(VirtualMachine virtualMachine){
		//TODO
	}
	
	

}
