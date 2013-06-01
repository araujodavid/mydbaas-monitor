package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import br.com.caelum.vraptor.ioc.Component;

/**
 * Class that manages the startup of knowledge base of MyDBaaS environment.
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 26, 2013 
 */

@Component
public class StarterRepository {

	//DBaaS create table SQL
	public static final String DBaaS_Table = "CREATE TABLE `dbaas` (\n" +
											 "`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
											 "`description` text,\n" +
											 "`record_date` datetime NOT NULL,\n" +
											 "`alias` varchar(45) NOT NULL,\n" +
											 "PRIMARY KEY (`id`)" +
											 ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	//Host create table SQL
	public static final String Host_Table = "CREATE TABLE `host` (\n" +
											"`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
											"`record_date` datetime NOT NULL,\n" +
											"`alias` varchar(45) NOT NULL,\n" +
											"`description` text,\n" +
											"`address` varchar(100) NOT NULL,\n" +
											"`port` int(11) NOT NULL,\n" +
											"`username` varchar(50) NOT NULL,\n" +
											"`password` varchar(75) NOT NULL,\n" +
											"`status` tinyint(4) DEFAULT NULL,\n" +
											"`host_name` varchar(75) DEFAULT NULL,\n" +
											"`host_hypervisor` varchar(75) DEFAULT NULL,\n" +
											"`host_cores` int(11) DEFAULT NULL,\n" +
											"`host_cpus` int(11) DEFAULT NULL,\n" +
											"`host_memory` double DEFAULT NULL,\n" +
											"`host_mhz` double DEFAULT NULL,\n" +
											"`host_model` varchar(75) DEFAULT NULL,\n" +
											"`dbaas` int(11) NOT NULL,\n" +
											"PRIMARY KEY (`id`),\n" +
											"KEY `fk_host_dbaas_idx` (`dbaas`),\n" +
											"CONSTRAINT `fk_host_dbaas` FOREIGN KEY (`dbaas`) REFERENCES `dbaas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
											") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	//VirtualMachine create table SQL
	public static final String VirtualMachine_Table = "CREATE TABLE `virtual_machine` (\n" +
													  "`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
													  "`address` varchar(100) NOT NULL,\n" +
													  "`username` varchar(50) NOT NULL,\n" +
													  "`port` int(11) NOT NULL,\n" +
													  "`password` varchar(75) DEFAULT NULL,\n" +
													  "`record_date` datetime NOT NULL,\n" +
													  "`alias` varchar(45) NOT NULL,\n" +
													  "`status` tinyint(4) NOT NULL,\n" +
													  "`description` text,\n" +
													  "`so` varchar(100) DEFAULT NULL,\n" +
													  "`kernel_name` varchar(60) DEFAULT NULL,\n" +
													  "`kernel_version` varchar(50) DEFAULT NULL,\n" +
													  "`architecture` varchar(10) DEFAULT NULL,\n" +
													  "`memory` float DEFAULT NULL,\n" +
													  "`swap` float DEFAULT NULL,\n" +
													  "`cpu_cores` int(11) DEFAULT NULL,\n" +
													  "`cpu_sockets` int(11) DEFAULT NULL,\n" +
													  "`cores_sockets` int(11) DEFAULT NULL,\n" +
													  "`key` varchar(100) DEFAULT NULL,\n" +
													  "`identifier_host` varchar(45) DEFAULT NULL,\n" +
													  "`dbaas` int(11) NOT NULL,\n" +
													  "`host` int(11) DEFAULT NULL,\n" +
													  "PRIMARY KEY (`id`),\n" +
													  "KEY `fk_virtual_machine_dbaas_idx` (`dbaas`),\n" +
													  "KEY `fk_virtual_machine_host_idx` (`host`),\n" +
													  "CONSTRAINT `fk_virtual_machine_dbaas` FOREIGN KEY (`dbaas`) REFERENCES `dbaas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
													  "CONSTRAINT `fk_virtual_machine_host` FOREIGN KEY (`host`) REFERENCES `host` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
													  ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	//DBMS create table SQL
	public static final String DBMS_Table = "CREATE TABLE `dbms` (\n" +
											"`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
											"`alias` varchar(45) NOT NULL,\n" +
											"`record_date` datetime NOT NULL,\n" +
											"`description` text,\n" +
											"`address` varchar(100) DEFAULT NULL,\n" +
											"`port` int(11) DEFAULT NULL,\n" +
											"`username` varchar(50) NOT NULL,\n" +
											"`password` varchar(75) NOT NULL,\n" +
											"`status` tinyint(4) NOT NULL,\n" +
											"`type` varchar(45) NOT NULL,\n" +
											"`virtual_machine` int(11) NOT NULL,\n" +
											"PRIMARY KEY (`id`),\n" +
											"KEY `fk_dbms_machine_idx` (`virtual_machine`),\n" +
											"CONSTRAINT `fk_dbms_virtual_machine` FOREIGN KEY (`virtual_machine`) REFERENCES `virtual_machine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
											") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	//Database create table SQL
	public static final String Database_Table = "CREATE TABLE `database` (\n" +
												"`id` int(11) NOT NULL AUTO_INCREMENT,\n" +
												"`description` text,\n" +
												"`record_date` datetime NOT NULL,\n" +
												"`alias` varchar(45) NOT NULL,\n" +
												"`name` varchar(100) NOT NULL,\n" +
												"`status` tinyint(4) NOT NULL,\n" +
												"`dbms` int(11) NOT NULL,\n" +
												"PRIMARY KEY (`id`),\n" +
												"KEY `fk_database_dbms_idx` (`dbms`),\n" +
												"CONSTRAINT `fk_database_dbms` FOREIGN KEY (`dbms`) REFERENCES `dbms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
												") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
    /**
     * Method to creates the table of resources
     * @param tableSQL
     * @return true if the table was created
     */
	public boolean createResourceTable(String tableSQL) {
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement(tableSQL);
						
			this.preparedStatement.executeUpdate();
			return true;
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return false;
	}//createResourceTable()
	
	/**
	 * Method to check the tables were created.
	 * @return a list of tables and their status
	 */
	public Map<String, Boolean> verifyTables() {
		Map<String, Boolean> tables = new HashMap<String, Boolean>();
		tables.put("dbaas", false);
		tables.put("host", false);
		tables.put("virtual_machine", false);
		tables.put("dbms", false);
		tables.put("database", false);
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select table_name from information_schema.tables where table_schema = database() and table_name not like '%_metric%';");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				String key = resultSet.getString("table_name");
				if (tables.containsKey(key)) {
					tables.put(key, true);
				}
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return tables;
	}//verifyTables()
}
