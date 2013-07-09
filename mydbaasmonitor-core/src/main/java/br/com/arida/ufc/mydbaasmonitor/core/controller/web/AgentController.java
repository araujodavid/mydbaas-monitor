package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web;

import static main.java.br.com.arida.ufc.mydbaasmonitor.core.util.Utils.i18n;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.engine.MetricProfile;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.engine.MonitoringCoordinator;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DBMSRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.DatabaseRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.HostRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 * Class that manages the front-end to configure and send agent to resources.
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since June 3, 2013
 */
@Resource
@Path("/agent")
public class AgentController {

	private Result result;
	private VirtualMachineRepository virtualMachineRepository;
	private DBMSRepository dbmsRepository;
	private DatabaseRepository databaseRepository;
	private HostRepository hostRepository;
	
	public AgentController(Result result, VirtualMachineRepository virtualMachineRepository, DBMSRepository dbmsRepository, DatabaseRepository databaseRepository, HostRepository hostRepository) {
		this.result = result;
		this.virtualMachineRepository = virtualMachineRepository;
		this.dbmsRepository = dbmsRepository;
		this.databaseRepository = databaseRepository;
		this.hostRepository = hostRepository;
	}
	
	@Path("/machine/{resourceID}")
	public void formMachine(int resourceID) {
		VirtualMachine virtualMachine = virtualMachineRepository.find(resourceID);
		virtualMachine.setDbmsList(dbmsRepository.getMachineDBMSs(resourceID));
		for (DBMS dbms : virtualMachine.getDbmsList()) {
			dbms.setDatabases(databaseRepository.getDBMSDatabases(dbms.getId()));
		}		
		result.include("machine", virtualMachine);
	}
	
	@Path("/host/{resourceID}")
	public void formHost(int resourceID) {
		Host host = hostRepository.find(resourceID);
		result.include("host", host);
	}
	
	@Path("/send")
	public void sendAgent(List<MetricProfile> profiles, int resourceID, String resourceType) {
		MonitoringCoordinator monitoringCoordinator = new MonitoringCoordinator();
		boolean resultAgent = false;
		VirtualMachine machine = null;
		Host host = null;
		//Checks if resource type is machine and recovers its DBMSs and databases
		try {
			if (resourceType.equals("machine")) {				
				machine = virtualMachineRepository.find(resourceID);
				machine.setDbmsList(dbmsRepository.getMachineDBMSs(resourceID));
				for (DBMS dbms : machine.getDbmsList()) {
					dbms.setDatabases(databaseRepository.getDBMSDatabases(dbms.getId()));
				}
				//Checks if resource has an agent already
				if (machine.getStatus() == false) {
					resultAgent = monitoringCoordinator.startNewAgentEnvironment(machine, profiles, machine.getDbmsList());	
				} else {
					resultAgent = monitoringCoordinator.updateAgentEnvironment(machine, profiles, machine.getDbmsList());	
				}						
			} else if (resourceType.equals("host")) {
				host = hostRepository.find(resourceID);
				//Checks if resource has an agent already
//				if (host.getStatus() == false) {
//					resultAgent = monitoringCoordinator.startNewAgentEnvironment(host, profiles, null);	
//				} else {
//					resultAgent = monitoringCoordinator.updateAgentEnvironment(host, profiles, null);	
//				}	
				try {
					Thread.sleep(5000);
					resultAgent = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//Checks whether the agent was sent and configured
		if (resultAgent) {
			result.include("status", true)
				  .include("agentNotice", i18n("agent.ok"));
			//Check what type of resource and makes the redirection - case ok
			if (resourceType.equals("machine")) {
				//Set monitoring status to true
				machine.setStatus(true);
				virtualMachineRepository.updateStatus(machine);
				for (MetricProfile metricProfile : profiles) {
					if (metricProfile.getType().equals("storage")) {
						if (metricProfile.getDbms() != null && !metricProfile.getDbms().isEmpty()) {
							for (Integer dbmsId : metricProfile.getDbms()) {
								dbmsRepository.updateStatus(true, dbmsId);
							}
						}
						if (metricProfile.getDatabases() != null && !metricProfile.getDatabases().isEmpty()) {
							for (Integer databaseId : metricProfile.getDatabases()) {
								databaseRepository.updateStatus(true, databaseId);						
							}
						}						
					}				
				}				
				result.redirectTo(VirtualMachineController.class).view(machine);
			} else if (resourceType.equals("host")) {
				//Set monitoring status to true
				host.setStatus(true);
				hostRepository.update(host);
				result.redirectTo(HostController.class).view(host);		
			}
		} else {
			result.include("status", false)
			      .include("agentNotice", i18n("agent.error"));
			//Check what type of resource and makes the redirection - case error
			if (resourceType.equals("machine")) {
				//Set monitoring status to false
				machine.setStatus(false);
				virtualMachineRepository.updateStatus(machine);
				for (MetricProfile metricProfile : profiles) {
					if (metricProfile.getType().equals("storage")) {
						if (metricProfile.getDbms() != null && !metricProfile.getDbms().isEmpty()) {
							for (Integer dbmsId : metricProfile.getDbms()) {
								dbmsRepository.updateStatus(false, dbmsId);
							}
						}
						if (metricProfile.getDatabases() != null && !metricProfile.getDatabases().isEmpty()) {
							for (Integer databaseId : metricProfile.getDatabases()) {
								databaseRepository.updateStatus(false, databaseId);							
							}
						}						
					}				
				}	
				result.redirectTo(VirtualMachineController.class).view(machine);
			} else if (resourceType.equals("host")) {
				//Set monitoring status to true
				host.setStatus(false);
				hostRepository.update(host);
				result.redirectTo(HostController.class).view(host);		
			}
		}		
	}
	
	@Path("/stop/{resourceType}/{resourceID}")
	public void stopAgent(String resourceType, int resourceID) {
		MonitoringCoordinator monitoringCoordinator = new MonitoringCoordinator();
		boolean resultAgent = false;
		VirtualMachine machine = null;
		Host host = null;
		
		try {
			if (resourceType.equals("machine")) {
				machine = virtualMachineRepository.find(resourceID);
				machine.setDbmsList(dbmsRepository.getMachineDBMSs(resourceID));
				for (DBMS dbms : machine.getDbmsList()) {
					dbms.setDatabases(databaseRepository.getDBMSDatabases(dbms.getId()));
				}
				resultAgent = monitoringCoordinator.stopAgentEnvironment(machine);
			} else if (resourceType.equals("host")) {
				host = hostRepository.find(resourceID);	
				resultAgent = monitoringCoordinator.stopAgentEnvironment(host);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		//Checks whether the agent was sent and configured
		if (resultAgent) {
			result.include("status", true).include("agentNotice", i18n("agent.stop.ok"));					
		} else {
			result.include("status", false).include("agentNotice", i18n("agent.stop.error"));
		}
		
		//Check what type of resource and makes the redirection - case ok
		if (resourceType.equals("machine")) {
			//Set monitoring status to false
			machine.setStatus(false);
			virtualMachineRepository.updateStatus(machine);
			dbmsRepository.updateAllStatus(false, resourceID);
			machine.setDbmsList(dbmsRepository.getMachineDBMSs(resourceID));
			for (DBMS dbms : machine.getDbmsList()) {
				databaseRepository.updateAllStatus(false, dbms.getId());
			}
			result.redirectTo(VirtualMachineController.class).view(machine);
		} else if (resourceType.equals("host")) {
			//Set monitoring status to false
			host.setStatus(false);
			hostRepository.update(host);
			result.redirectTo(HostController.class).view(host);
		}
	}
}
