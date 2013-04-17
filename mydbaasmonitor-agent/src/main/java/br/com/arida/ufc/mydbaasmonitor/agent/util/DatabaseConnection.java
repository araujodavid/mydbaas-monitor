package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 25, 2013
 * 
 */
public class DatabaseConnection {

	private static DatabaseConnection uniqueInstance;
	private List<Object> databases;
	
	private DatabaseConnection() {}

	public static DatabaseConnection getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DatabaseConnection();
	    }
	    return uniqueInstance;
	}
	
	public Connection getConnection(String database) {
		//TODO
		return null;
	}
	
	private Connection connect() {
		//TODO
		return null;
	}
	
	public void loadConnectionProperties(Properties properties) {
		this.databases = new ArrayList<Object>();
		//TODO
	}

	public List<Object> getDatabases() {
		return databases;
	}	
}
