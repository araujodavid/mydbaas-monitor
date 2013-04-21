package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.google.gson.Gson;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;

/**
 * Class that manages the connection to databases
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since March 25, 2013 
 */
public class DatabaseConnection {
	
	private final String MySQL_DRIVER = "com.mysql.jdbc.Driver";
	private final String PostgreSQL_DRIVER = "org.postgresql.Driver";
	private final String MySQL_URL = "jdbc:mysql://localhost";
	private final String PostgreSQL_URL = "jdbc:postgresql://localhost";

	private static DatabaseConnection uniqueInstance;
	private List<DBMS> dbmsList;
	
	private DatabaseConnection() {}

	public static DatabaseConnection getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DatabaseConnection();
	    }
	    return uniqueInstance;
	}
	
	/**
	 * Method that receives the code from a database and returns a connection
	 * @param databaseId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection(int databaseId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		for (DBMS dbms : dbmsList) {			
			for (Database database : dbms.getDatabases()) {
				if (database.getId() == databaseId) {
					connection = this.connect(dbms.getType(), dbms.getPort(), database.getName(), dbms.getUser(), dbms.getPassword());
				}
			}
		}
		return connection;
	}
	
	/**
	 * Method to create a connection to a particular database
	 * @param dbmsName
	 * @param port
	 * @param database
	 * @param username
	 * @param password
	 * @return an open connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection connect(String dbmsType, int port, String database, String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		switch (dbmsType) {
		case "MySQL":
			Class.forName(MySQL_DRIVER);
			connection = DriverManager.getConnection(MySQL_URL+":"+port+"/"+database, username, password);
			break;
		case "PostgreSQL":
			Class.forName(PostgreSQL_DRIVER);
			connection = DriverManager.getConnection(PostgreSQL_URL+":"+port+"/"+database, username, password);
			break;
		}
		return connection;
	}
	
	/**
	 * Method that carries the information about the DBMS and its databases
	 * @param properties
	 */
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
