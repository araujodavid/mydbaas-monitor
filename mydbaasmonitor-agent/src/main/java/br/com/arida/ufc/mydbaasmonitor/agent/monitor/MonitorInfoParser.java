package main.java.br.com.arida.ufc.mydbaasmonitor.agent.monitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since March 5, 2013 
 */
public class MonitorInfoParser {
	
	//Single instance of the object
	private static MonitorInfoParser uniqueInstance;
	//Unique identifier of the resource on the server
	private Integer identifier;
	//Resource type
	private String type;
	//Geral URL
	private String server;
	//Properties agent configuration file
	private Properties properties;

	private MonitorInfoParser() {
		this.properties = new Properties();
	}

	public static MonitorInfoParser getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MonitorInfoParser();
	    }
	    return uniqueInstance;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
		
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void loadContextFile(String path) throws FileNotFoundException, IOException{
		this.properties.load(new FileInputStream(path));
	}
	
	public void loadProperties() {
		this.identifier = Integer.valueOf(this.properties.getProperty("identifier"));
		this.type = this.properties.getProperty("type");
		this.server = this.properties.getProperty("server");
	}
}
