package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jruby.RubyProcess.Sys;

import com.google.gson.Gson;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.engine.MetricProfile;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.engine.MonitoringCoordinator;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.SSHController;

public class MainTest {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		SSHController sshController = SSHController.getInstance();
//		try {
//			Session session = sshController.createSessionWithKey("ec2-50-16-126-210.compute-1.amazonaws.com", "ubuntu", 22, "/home/david/Documents/davidtici.pem");
//			ChannelExec channelExec = sshController.openChannelShellCommands(session);
//			channelExec.setCommand("rm /home/ubuntu/tpch-alter.sql");
//			channelExec.connect();
//		} catch (JSchException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Reflections reflections = new Reflections("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database");
//
//	    Set<Class<? extends AbstractDatabaseMetric>> subTypes = reflections.getSubTypesOf(AbstractDatabaseMetric.class);
//	    
//	    for (Class<? extends AbstractDatabaseMetric> class1 : subTypes) {
//			System.out.println(class1.getSimpleName());
//		}
		
//		MetricRepository repository = new MetricRepository();
//		System.out.println(repository.makeQuerySQL(Cpu.class, MetricRepository.METRIC_SINGLE_TYPE, 3, MetricRepository.LAST_COLLECTION, null, null));
//		System.out.println(repository.makeQuerySQL(Cpu.class, MetricRepository.METRIC_MULTI_TYPE, 3, MetricRepository.LAST_COLLECTION, null, null));
		
//		List<DBMS> dbms = new ArrayList<DBMS>();
//		
//		DBMS d1 = new DBMS();
//		d1.setAddress("10.1.1.1");
//		d1.setAlias("D1");
//		d1.setDescription("D1 description");
//		d1.setId(1);
//		d1.setPassword("12345");
//		d1.setPort(1234);
//		d1.setRecordDate(DataUtil.convertDateToString(new Date()));
//		d1.setStatus(true);
//		d1.setType("MySQL");
//		d1.setUser("david");
//		
//		List<Database> d1db = new ArrayList<Database>();
//		Database db1 = new Database();
//		db1.setAlias("DB1");
//		db1.setDescription("DB1 description");
//		db1.setId(1);
//		db1.setName("mydbaasmonitor");
//		db1.setRecordDate(DataUtil.convertDateToString(new Date()));
//		db1.setStatus(true);
//		d1db.add(db1);
//				
//		Database db2 = new Database();
//		db2.setAlias("DB2");
//		db2.setDescription("DB2 description");
//		db2.setId(2);
//		db2.setName("mydbaas");
//		db2.setRecordDate(DataUtil.convertDateToString(new Date()));
//		db2.setStatus(true);
//		d1db.add(db2);
//				
//		d1.setDatabases(d1db);
//		
//		Gson gson = new Gson();
//		
//		System.out.println(gson.toJson(d1));
		
		SSHController sshController = SSHController.getInstance();
		
		try {
			Session session = sshController.createSession("192.168.1.2", "david", "root", 22);			

//			String path = sshController.getCurrentPath(session);
//			System.out.println(path);
//
//			boolean folderStatus = sshController.createAgentFolder(path, session);
//
//			boolean agentFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/agent.jar", path, session);
//	
//			boolean contextFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/context.conf", path, session);
//
//			boolean requirementsFile = sshController.sendFile("/home/david/Desktop/MyDBaaSMonitor/requirements.zip", path, session);
//			
//			boolean extractFile = sshController.extractRequirementsFile(path, session);
//			
//			boolean moveFile = sshController.moveRequirementsFile("root", path, session);
//			
//			boolean result = sshController.startAgent(path, session);
			
			String pid = sshController.getAgentPID(session);
			System.out.println(pid);
			boolean result = sshController.stopAgent("root", pid, session);
			System.out.println(result);
			
			session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
