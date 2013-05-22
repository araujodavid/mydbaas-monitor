package main.java.br.com.arida.ufc.mydbaasframework.agent.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.Database;
import com.google.gson.Gson;

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
	 * Method to create a connection from code of the DBMS/Database
	 * @param dbmsId
	 * @param databaseId
	 * @return an object array with database type and an open connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Object[] getConnection(Integer dbmsId, Integer databaseId) throws ClassNotFoundException, SQLException {
		//firt index is the DBMS type
		//second index is the connection
		Object[] params = new Object[3];
		//Check if the metric is DBMS level or database level
		if (dbmsId != null) {
			for (DBMS dbms : this.dbmsList) {
				if (dbms.getId() == dbmsId) {
					String databaseDefault = null;
					if (dbms.getType().equals("MySQL")) {
						databaseDefault = "mysql";
					} else {
						databaseDefault = "postgres";
					}
					params[0] = dbms.getType();
					params[1] = this.connect(dbms.getType(), dbms.getPort(), databaseDefault, dbms.getUser(), dbms.getPassword());
				}
			}
		} else if (databaseId != null) {
			for (DBMS dbms : this.dbmsList) {			
				for (Database database : dbms.getDatabases()) {
					if (database.getId() == databaseId) {
						params[0] = dbms.getType();
						params[1] = this.connect(dbms.getType(), dbms.getPort(), database.getName(), dbms.getUser(), dbms.getPassword());
						params[2] = database.getName();
					}
				}
			}
		}
		return params;
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
		Gson gson = new Gson();
		for (Object dbmsPropertie : properties.keySet()) {
			if (dbmsPropertie.toString().contains("dbms.")) {				
				this.dbmsList.add(gson.fromJson(properties.getProperty(dbmsPropertie.toString()), DBMS.class));
			}
		}
	}
	
	public ResultSet executeSQL(Connection connection, String sql, Map<Integer, Object> params) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		
		preparedStatement = connection.prepareStatement(sql);
		
		if ((params != null) && (!params.isEmpty())) {
			for (Map.Entry<Integer, Object> item : params.entrySet()) {
				preparedStatement.setObject(item.getKey(), item.getValue());
			}
		}		
		resultSet = preparedStatement.executeQuery();
		return resultSet;
	}

	public List<DBMS> getDBMSs() {
		return dbmsList;
	}	
}
