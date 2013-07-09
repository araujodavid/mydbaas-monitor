package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

import java.io.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Class to handle FTP and SSH commands. Using JSch lib.
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since June 3, 2013
 */
public class SSHController {
	
	private static SSHController sshController;
	
	protected SSHController() {}
	
	public static SSHController getInstance() {
		if (sshController == null) {
			sshController = new SSHController();
		}		
		return sshController;
	}
	
	//Method to create an ssh session
	public Session createSession(String address, String username, String password, int port) throws JSchException{
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, address, port);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		return session;
	}
	
	//Method to create an ssh session
	public Session createSessionWithKey(String host, String username, int port, String key) throws JSchException{
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, host, port);
		jsch.addIdentity(key);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		return session;
	}
	
	//Method to open an FTP channel
	private ChannelSftp openChannelSftp(Session session) throws JSchException{
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		return channelSftp;
	}
	
	//Method to send a file to the host
	private void sendFile(String localFile, String destination, ChannelSftp channelSftp) throws SftpException{
		channelSftp.put(localFile, destination);
	}
	
	//Method to read a file from the host
	public BufferedReader getFile(String hostFile, ChannelSftp channelSftp) throws SftpException {
		InputStream out = null;
        out = channelSftp.get(hostFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
		return br;
	}
	
	//Method to open a channel to send commands
	public ChannelExec openChannelShellCommands(Session session) throws JSchException{
		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		return channelExec;
	}
	
	public String getReturnCommand(InputStream input, Channel channel) throws IOException {
		String result = null;
		byte[] tmp = new byte[1024];
		while (true) {
			while (input.available() > 0) {
				int i = input.read(tmp, 0, 1024);
		        if (i < 0)
		        	break;
		        	result = new String(tmp, 0, i);
	        }
	        if (channel.isClosed()) {
	        	if(input.available() > 0) {
	        		int i = input.read(tmp, 0, 1024);
	        		result = new String(tmp, 0, i);
	            }
	            break;
	        }
		}
		return result;
	}
	
	public String getCurrentPath(Session session) {
		String result = null;
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			((ChannelExec)channel).setCommand("pwd");
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();			
			result = SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return result.trim();
	}
	
	public String getAgentPID(Session session) {
		String result = null;
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(false);
			((ChannelExec)channel).setCommand("ps -ef | grep agent.jar | grep -v grep | awk '{print $2}'");
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();			
			result = SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return result;
	}
	
	public boolean createAgentFolder(String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "mkdir "+path+"/MyDBaaSMonitor";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean sendFile(String file, String path, Session session) {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = this.openChannelSftp(session);
			channelSftp.connect();
			this.sendFile(file, path+"/MyDBaaSMonitor", channelSftp);
			channelSftp.disconnect();
		} catch (JSchException e) {
			return false;
		} catch (SftpException e) {
			return false;
		}
		return true;
	}
	
	public boolean stopAgent(String password, String agentPID, Session session) {
		InputStream in = null;
		OutputStream out = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "sudo kill "+agentPID;
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			out = channel.getOutputStream();
			channel.connect();
			out.write((password+"\n").getBytes());
		    out.flush();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			System.out.println(e);
		}				
		return true;
	}
	
	public boolean startAgentHost(String password, String path, Session session) {
		InputStream in = null;
		OutputStream out = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "sudo -S -p '' nohup java -jar "+path+"/MyDBaaSMonitor/agent.jar "+path+" >/dev/null 2>&1&";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			out = channel.getOutputStream();
			channel.connect();
			out.write((password+"\n").getBytes());
		    out.flush();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			System.out.println(e);
		}				
		return true;
	}
	
	public boolean startAgent(String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(false);
			String command = "nohup java -jar "+path+"/MyDBaaSMonitor/agent.jar "+path+" >/dev/null 2>&1&";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean deleteAgentFolder(String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "rm -r "+path+"/MyDBaaSMonitor";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean deleteAgentContextFile(String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "rm "+path+"/MyDBaaSMonitor/context.conf";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean renameContextFile(String olderFileName, String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "mv "+path+"/MyDBaaSMonitor/"+olderFileName+" "+path+"/MyDBaaSMonitor/context.conf";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean extractRequirementsFile(String path, Session session) {
		InputStream in = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "unzip "+path+"/MyDBaaSMonitor/requirements.zip -d "+path+"/MyDBaaSMonitor";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			channel.connect();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			return false;
		}				
		return true;
	}
	
	public boolean moveRequirementsFile(String password, String path, Session session) {
		InputStream in = null;
		OutputStream out = null;
		Channel channel = null;
		try {
			channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			String command = "sudo -S -p '' mv "+path+"/MyDBaaSMonitor/*.so /usr/lib/";
			((ChannelExec)channel).setCommand(command);
			channel.setInputStream(null);
			in = channel.getInputStream();
			out = channel.getOutputStream();
			channel.connect();
			out.write((password+"\n").getBytes());
		    out.flush();
			SSHController.getInstance().getReturnCommand(in, channel);
			channel.disconnect();
		} catch (JSchException e1) {
			return false;
		} catch (IOException e) {
			System.out.println(e);
		}				
		return true;
	}
}
