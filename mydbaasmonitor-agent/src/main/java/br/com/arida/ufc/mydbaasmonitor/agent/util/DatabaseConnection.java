package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

/**
 * 
 * @author Daivd Araújo
 * @version 1.0
 * @since March 25, 2013
 * 
 */
public class DatabaseConnection {

	private static DatabaseConnection uniqueInstance;
	
	private DatabaseConnection() {}

	public static DatabaseConnection getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DatabaseConnection();
	    }
	    return uniqueInstance;
	}
}
