package main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;

public class MyDBaaSConnection {
	
private static MyDBaaSConnection uniqueInstance;
	
	private MyDBaaSConnection() {}

	public static MyDBaaSConnection getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MyDBaaSConnection();
	    }
	    return uniqueInstance;
	}
	
	public Connection getConnection(Database database) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		
		switch (database.getDbms().getType()) {
		case "MySQL":
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+database.getDbms().getAddress()+":"+database.getDbms().getPort()+"/"+database.getName(), database.getDbms().getUser(), database.getDbms().getPassword());
			break;
		case "PostgreSQL":
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://"+database.getDbms().getAddress()+":"+database.getDbms().getPort()+"/"+database.getName(), database.getDbms().getUser(), database.getDbms().getPassword());
			break;
		}		
		return connection;
	}
}
