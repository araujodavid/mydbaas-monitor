package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;
import java.util.Date;
import java.util.List;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.AbstractController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common.GenericController;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBaaSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.HostRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;

/**
 * Class that manages the methods that the front-end hosts accesses.
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since May 13, 2013
 * Front-end: web/WEB-INF/jsp/host
 */

@Resource
public class HostController extends AbstractController implements GenericController<Host> {

	private HostRepository hostRepository;
	private DBaaSRepository dBaaSRepository;
	private VirtualMachineRepository virtualMachineRepository;
	
	public HostController(Result result, Validator validator, HostRepository hostRepository, DBaaSRepository dBaaSRepository, VirtualMachineRepository virtualMachineRepository) {
		super(result, validator);
		this.hostRepository = hostRepository;
		this.dBaaSRepository = dBaaSRepository;
		this.virtualMachineRepository = virtualMachineRepository;
	}

	@Path("/hosts")
	@Override
	public void redirect() {
		this.result.redirectTo(this).list();		
	}

	@Path("/hosts/list")
	@Override
	public List<Host> list() {
		List<Host> hostList = hostRepository.all();
		//Information for pie chart
		int amountActive = 0;
		int amountNotActive = 0;
		int amountWithVM = 0;
		int amountWithoutVM = 0;
		for (Host host : hostList) {
			if (host.getStatus() == true) {
				amountActive++;
			} else {
				amountNotActive++;
			}
			host.setMachines(virtualMachineRepository.getHostMachines(host.getId()));
			if (host.getMachines().isEmpty()) {
				amountWithoutVM++;
			} else {
				amountWithVM++;
			}
		}
		this.result
		.include("amountActive", amountActive)
		.include("amountNotActive", amountNotActive)
		.include("amountWithVM", amountWithVM)
		.include("amountWithoutVM", amountWithoutVM);
		return hostList;
	}

	@Path("/hosts/new")
	@Override
	public void form() {
		//Includes the current date
		//List available DBaaS
		this.result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("availableDBaaS", dBaaSRepository.all());
	}

	@Path("/hosts/add")
	public void add(final Host host, final String confirmPassword) {
		//Validations by vRaptor
		validator.checking(new Validations() {{
			that(!(host.getEnvironment().getId() == 0), "Environment", "host.environment.empty");
			that(!(host.getAlias() == null), "Alias", "host.alias.empty");
	        that(!(host.getAddress() == null), "Address", "host.address.empty");
	        that(!(host.getUser() == null), "Username", "host.username.empty");
	        that(!(host.getPort() == null), "Port", "host.port.empty");
	        if (host.getPassword() != null || confirmPassword != null) {
	        	that(host.getPassword().equals(confirmPassword), "Password", "host.password.not.checked");
			}	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(HostController.class).form();
		
		hostRepository.save(host);
		result
		.include("notice", i18n("host.save.ok"))
		.redirectTo(this).list();		
	}

	@Path("/hosts/edit/{entity.id}")
	@Override
	public Host edit(Host entity) {
		this.result
		.include("availableDBaaS", dBaaSRepository.all());
		return hostRepository.find(entity.getId());
	}

	@Path("/hosts/update")
	@Override
	public void update(final Host host) {
//		if (host.getStatus() == null) { 
//			host.setStatus(false); 
//		}
		//Validations by vRaptor
		validator.checking(new Validations() { {
			that(!(host.getAlias() == null), "Alias", "host.alias.empty");
	        that(!(host.getAddress() == null), "Address", "host.host.empty");
	        that(!(host.getUser() == null), "Username", "host.username.empty");
	        that(!(host.getPort() == null), "Port", "host.port.empty");	        
	    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(HostController.class).edit(host);
		
		hostRepository.update(host);
		result
		.include("notice", i18n("host.update.ok"))
		.redirectTo(this).view(host);
	} //update()
	
	@Path("/hosts/password")
	public void passwordUpdate(final Host host, final String confirmPassword){
		//Validations by vRaptor
			validator.checking(new Validations() { {
				if (host.getPassword() != null || confirmPassword != null) {
		        	that(host.getPassword().equals(confirmPassword), "Password", "host.password.not.checked");
				} else {
					validator.add(new ValidationMessage("Please enter a password. It can't be empty.", "Password"));
				}    
		    } });
		//If some validation is triggered are sent error messages to page
		validator.onErrorForwardTo(HostController.class).edit(host);
		
		//If everything is ok the object is sent to the ResourceManager and the user is redirected to the page that lists the registered resources
		hostRepository.updatePassword(host);
		result
		.include("notice", i18n("host.update.password.ok"))
		.redirectTo(this).view(host);
	}

	@Path("/hosts/view/{host.id}")
	@Override
	public Host view(Host host) {
		host = hostRepository.find(host.getId());
		host.setEnvironment(dBaaSRepository.find(host.getEnvironment().getId()));
		host.setMachines(virtualMachineRepository.getHostMachines(host.getId()));
		result
		.include("current_date", DataUtil.convertDateToStringUI(new Date()))
		.include("availableDBaaS", dBaaSRepository.all());
		return host;
	}
	
	/**
	 * Override methods
	 */

	@Override
	public void delete(Host entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void add(Host entity) {
		// TODO Auto-generated method stub		
	}

}
