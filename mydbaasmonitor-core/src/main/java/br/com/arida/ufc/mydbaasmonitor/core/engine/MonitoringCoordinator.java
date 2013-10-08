package main.java.br.com.arida.ufc.mydbaasmonitor.core.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.GenericResource;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.SSHController;
import com.google.gson.Gson;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Class that manages the methods to configure and send agent to resources.
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since June 3, 2013
 */
public class MonitoringCoordinator {
	
	/**
	 * Method to check ssh connection for a given resource
	 * @param address
	 * @param username
	 * @param password
	 * @param port
	 * @return true if ok, otherwise false
	 */
	public boolean testConnection(String address, String username, String password, int port) {
		Session session = null;
		try {
			session = SSHController.getInstance().createSession(address, username, password, port);
		} catch (JSchException e) {
			e.printStackTrace();
		}
		if (session.isConnected()) {
			return true;
		}
		return false;		
	}
	
	/**
	 * Method that creates the context for the agent.
	 * @param resourceType
	 * @param profiles
	 * @param identifier
	 * @param dbmsList
	 * @return a string with the agent context
	 */
	public String createAgentContext(String resourceType, List<MetricProfile> profiles, int identifier, List<DBMS> dbmsList) {
		StringBuilder agentContext = new StringBuilder();
		agentContext.append("#-----------------------------\n")
					.append("# Information of the Resource\n")
					.append("#-----------------------------\n")
					.append("\n")
					.append("# Unique code to identify the resource on the server\n")
					.append("identifier = "+String.valueOf(identifier)+"\n")
					.append("type = "+resourceType+"\n")	
					.append("# Geral URL\n")
					.append("server = http://10.101.10.199:8080/mydbaasmonitor/\n")
					.append("\n");
		
		if (resourceType.equals("machine")) {
			agentContext.append("# URL to send request about system:\n")
						.append("about = machine/info\n")
						.append("\n")
						.append("\n");
		} else if (resourceType.equals("host")) {
			agentContext.append("# URL to send request about system:\n")
			.append("about = host/info\n")
			.append("\n")
			.append("\n");
		}	
							
		//Checks list of DBMS
		if (dbmsList != null && !dbmsList.isEmpty()) {
			Gson gson = new Gson();
			String jsonDBMSs = gson.toJson(dbmsList);
			agentContext.append("# Information of DBMSs\n")
						.append("dbmss = "+jsonDBMSs+"\n")
						.append("\n");
		}
		//Creates the properties of the metrics that will be monitored
		for (MetricProfile metricProfile : profiles) {
			if (metricProfile.getCycle() > 0) {
				agentContext.append(metricProfile.getName()+".url = "+metricProfile.getType()+"/"+metricProfile.getName().toLowerCase()+"\n")
							.append(metricProfile.getName()+".cycle = "+metricProfile.getCycle()+"\n");
				if (metricProfile.getDbms() != null && !metricProfile.getDbms().isEmpty()) {
					agentContext.append(metricProfile.getName()+".dbms = "+MetricProfile.printList(metricProfile.getDbms())+"\n");
				}
				if (metricProfile.getDatabases() != null && !metricProfile.getDatabases().isEmpty()) {
					agentContext.append(metricProfile.getName()+".databases = "+MetricProfile.printList(metricProfile.getDatabases())+"\n");
				}
				agentContext.append("\n");
			}			
		}		
		return agentContext.toString();
	}
	
	public boolean startNewAgentEnvironment(Object resource, List<MetricProfile> profiles, List<DBMS> dbmsList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getId = AbstractEntity.class.getDeclaredMethod("getId", null);
		Method getAddress = GenericResource.class.getDeclaredMethod("getAddress", null);
		Method getPort = GenericResource.class.getDeclaredMethod("getPort", null);
		Method getUser = GenericResource.class.getDeclaredMethod("getUser", null);
		Method getPassword = GenericResource.class.getDeclaredMethod("getPassword", null);
		int id = Integer.class.cast(getId.invoke(resource, null));
		String address = String.valueOf(getAddress.invoke(resource, null));
		int port = Integer.class.cast(getPort.invoke(resource, null));
		String username = String.valueOf(getUser.invoke(resource, null));
		String password = String.valueOf(getPassword.invoke(resource, null));
		
		//Creates configuration context of the agent
		String agentContext = this.createAgentContext(resource.toString(), profiles, id, dbmsList);
		
		//Creates agent context file
		String fileName = "context"+formatDate(new Date())+".conf";
		this.createFileAgent(agentContext, fileName);
		
		//Accesses the resources to start the agent installation
		SSHController sshController = SSHController.getInstance();
		Session session;
		try {
			//Creates the SSH session
			session = sshController.createSession(address, username, password, port);
			//Gets the path
			String path = sshController.getCurrentPath(session);
			//Creates agent folder
			boolean folderStatus = sshController.createAgentFolder(path, session);
			//Sends the agent runnable jar
			boolean agentFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/agent.jar", path, session);
			//Sends the agent context file
			boolean contextFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/"+fileName, path, session);
			//Sends the requirements file
			boolean requirementsFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/requirements.zip", path, session);
			//Rename the agent context file
			boolean renameContextFile = sshController.renameContextFile(fileName, path, session);
			//Extract the requirements file
			boolean extractFile = sshController.extractRequirementsFile(path, session);
			//Move the requirements files
			boolean moveFile = sshController.moveRequirementsFile(password, path, session);
			//Checks whether the files were sent
			if (!folderStatus || !agentFile || !contextFile || !requirementsFile || !extractFile || !moveFile || !renameContextFile) {
				return false;
			} else {
				//Start agent
				boolean startAgent;
				if (resource.toString().equals("host")) {
					startAgent = sshController.startAgentHost(password, path, session);
				} else {
					startAgent = sshController.startAgent(path, session);
				}
				if (!startAgent) {
					return false;
				}
			}			
		} catch (JSchException e) {
			return false;
		}
		return true;
	}
	
	public boolean updateAgentEnvironment(Object resource, List<MetricProfile> profiles, List<DBMS> dbmsList) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method getId = AbstractEntity.class.getDeclaredMethod("getId", null);
		Method getAddress = GenericResource.class.getDeclaredMethod("getAddress", null);
		Method getPort = GenericResource.class.getDeclaredMethod("getPort", null);
		Method getUser = GenericResource.class.getDeclaredMethod("getUser", null);
		Method getPassword = GenericResource.class.getDeclaredMethod("getPassword", null);
		int id = Integer.class.cast(getId.invoke(resource, null));
		String address = String.valueOf(getAddress.invoke(resource, null));
		int port = Integer.class.cast(getPort.invoke(resource, null));
		String username = String.valueOf(getUser.invoke(resource, null));
		String password = String.valueOf(getPassword.invoke(resource, null));
		
		//Creates configuration context of the agent
		String agentContext = this.createAgentContext(resource.toString(), profiles, id, dbmsList);
		
		//Creates agent context file
		String fileName = "context"+formatDate(new Date())+".conf";
		this.createFileAgent(agentContext, fileName);
		
		//Accesses the resources to start the agent installation
		SSHController sshController = SSHController.getInstance();
		Session session;
		try {
			//Creates the SSH session
			session = sshController.createSession(address, username, password, port);
			//Gets the path
			String path = sshController.getCurrentPath(session);
			//Delete the agent context file
			boolean deleteContextFile = sshController.deleteAgentContextFile(path, session);
			//Sends the agent context file
			boolean contextFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/"+fileName, path, session);
			//Rename the agent context file
			boolean renameContextFile = sshController.renameContextFile(fileName, path, session);
			//Checks whether the files were sent
			if (!deleteContextFile || !contextFile || !renameContextFile) {
				return false;
			} else {
				//Start agent
				boolean startAgent;
				if (resource.toString().equals("host")) {
					startAgent = sshController.startAgentHost(password, path, session);
				} else {
					startAgent = sshController.startAgent(path, session);
				}
				if (!startAgent) {
					return false;
				}
			}			
		} catch (JSchException e) {
			return false;
		}
		return true;
	}
	
	public boolean stopAgentEnvironment(Object resource) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
		Method getId = AbstractEntity.class.getDeclaredMethod("getId", null);
		Method getAddress = GenericResource.class.getDeclaredMethod("getAddress", null);
		Method getPort = GenericResource.class.getDeclaredMethod("getPort", null);
		Method getUser = GenericResource.class.getDeclaredMethod("getUser", null);
		Method getPassword = GenericResource.class.getDeclaredMethod("getPassword", null);
		int id = Integer.class.cast(getId.invoke(resource, null));
		String address = String.valueOf(getAddress.invoke(resource, null));
		int port = Integer.class.cast(getPort.invoke(resource, null));
		String username = String.valueOf(getUser.invoke(resource, null));
		String password = String.valueOf(getPassword.invoke(resource, null));
		
		//Accesses the resources to start the agent installation
		SSHController sshController = SSHController.getInstance();
		Session session;
		try {
			//Creates the SSH session
			session = sshController.createSession(address, username, password, port);
			//Gets the path
			String path = sshController.getCurrentPath(session);
			//Gets agent PID
			String agentPID = sshController.getAgentPID(session);
			//Stop agent
			boolean stopAgent = sshController.stopAgent(password, agentPID, session);			
			//Checks whether the files were sent
			if (!stopAgent) {
				return false;
			} else {
				//Remove agent folder
				boolean removeFolder = sshController.deleteAgentFolder(path, session);
				if (!removeFolder) {
					return false;
				}
			}	
		} catch (JSchException e) {
			return false;
		}
		return true;
	}
	
	public void createFileAgent(String agentContext, String filename) {
		File file = new File("/home/david/Desktop/MyDBaaSMonitor/"+filename);
		BufferedWriter writer;
		try {
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(agentContext);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private static String formatDate(Date date) {   
        if (date == null)   
            return null;           
        String result = null;   
        try {   
            DateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss"); 
            result = formatter.format(date);   
        } catch (Exception e) {               
            System.err.println(e.getMessage());
        }   
        return result;   
	}	
}
