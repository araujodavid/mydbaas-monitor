package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;

/**
 * 
 * @author Daivd Araújo
 * @version 2.0
 * @since March 25, 2013
 * 
 */
public class DatabaseConnection {
	
	private final String MySQL_DRIVER = "";
	private final String PostgreSQL_DRIVER = "";
	private final String MySQL_URL = "";
	private final String PostgreSQL_URL = "";

	private static DatabaseConnection uniqueInstance;
	private List<DBMS> dbmsList;
	
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
	
	public void loadDBMSProperties(Properties properties) {
		this.dbmsList = new ArrayList<DBMS>();
		for (Object dbmsPropertie : properties.keySet()) {
			if (dbmsPropertie.toString().contains("dbms.")) {
				Gson gson = new Gson();
				this.dbmsList.add(gson.fromJson(properties.getProperty(dbmsPropertie.toString()), DBMS.class));
			}
		}
	}

	public List<DBMS> getDBMSs() {
		return dbmsList;
	}	
}
