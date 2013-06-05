package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

import java.io.*;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Class to handle FTP and SSH commands. Using JSch lib.
 * @author David Araújo - @araujodavid
 * @version 1.0
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
	public Session createSession(String host, String username, String password, int port) throws JSchException{
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, host, port);
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
	public ChannelSftp openChannelSftp(Session session) throws JSchException{
		ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
		return channelSftp;
	}
	
	//Method to send a file to the host
	public void sendFile(String localFile, String destination, ChannelSftp channelSftp) throws SftpException{
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
	

}
