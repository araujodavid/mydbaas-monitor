package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPool {

	private static ConnectionPool uniqueInstance;
	private String schema;
	private int port;
	private String username;
	private String password;
	
	private ConnectionPool() {}

	public static ConnectionPool getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ConnectionPool();
	    }
	    return uniqueInstance;
	}

	public String getSchema() {
		return schema;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setConfig(String schema, int port, String username, String password) {
		this.schema = schema;
		this.port = port;
		this.username = username;
		this.password = password;
	}	
	
	public Connection getConnection() {
		return connect();
	}
	
	private Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("The MySQL driver can not be loaded!");
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:"+port+"/"+schema, username, password);
		} catch (SQLException e) {
			System.out.println("Unable to connect to the database!");
			e.printStackTrace();
		}
		return connection;
	}
}
