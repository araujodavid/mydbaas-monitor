package main.java.br.com.arida.ufc.mydbaasframework.core.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.core.repository.connection.AbstractDatabaseConnection;
import main.java.br.com.arida.ufc.mydbaasframework.core.util.TypeTranslater;
import com.sun.xml.internal.ws.util.StringUtils;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013 
 */

public abstract class MetricRepository<T extends AbstractDatabaseConnection> {
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private List<String> tables = null;
    private T databasePool;
    
    public MetricRepository() {
    	this.tables = this.getTables();
    }

	/**
	 * Method to save a metric in the database
	 * @param metric - given metric object
	 * @param machine
	 * @param recordDate
	 * @return true if the metric is saved
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public boolean saveMetric(Object metric, int identifier, String recordDate, int dbms, int database) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (this.checkTable(metric)) {
			return this.insertMetric(metric, identifier, recordDate, dbms, database);			
		} else {
			if (this.createMetricTable(metric)) {
				return this.insertMetric(metric, identifier, recordDate, dbms, database);
			}			
		}
		return false;
	}//saveMetric()
	
	/**
	 * Method to save a metric in the database
	 * @param metric
	 * @param identifier
	 * @param recordDate
	 * @return true if the metric is saved
	 */
	private boolean insertMetric(Object metric, int identifier, String recordDate, int dbms, int database) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		List<Field> fields = this.getMetricFields(metric);
		Class<?> clazz = metric.getClass();	
		
		try {
			this.connection = this.databasePool.getConnection();
			this.preparedStatement = this.connection.prepareStatement(this.makeInsertSQL(metric, fields, dbms, database));
			
			//Gets the metric values ​​and configures the SQL query
			int count = 1;
			for (Field field : fields) {
				Method method;
				if (field.getName().toLowerCase().contains(clazz.getSimpleName().toLowerCase())) {
					method = clazz.getDeclaredMethod("get"+StringUtils.capitalize(field.getName()), null);
					this.preparedStatement.setObject(count, method.invoke(metric, null));
				}
				count++;
			}			
			//Setting the identifier and record date
			if (dbms != 0) {
				this.preparedStatement.setObject(count, dbms);
			} else if (database != 0) {
				this.preparedStatement.setObject(count, database);
			} else {
				this.preparedStatement.setObject(count, identifier);
			}			
			this.preparedStatement.setObject(count+1, recordDate);
			
			this.preparedStatement.executeUpdate();
			return true;
		} 
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
        }		
		return false;
	}//insertMetric()
	
	/**
	 * Method to create a table in the database for a given metric
	 * @param metric - given metric object
	 * @return true if the table is created
	 */
	public boolean createMetricTable(Object metric) {
		List<Field> fields = this.getMetricFields(metric);
		String tableSQL = this.makeCreateTableSQL(metric, fields);
		try {
			connection = this.databasePool.getConnection();
			preparedStatement = connection.prepareStatement(tableSQL);
						
			this.preparedStatement.executeUpdate();
			this.tables.add(metric.getClass().getSimpleName().toLowerCase()+"_metric");
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
	}//createMetricTable()
	
	/**
	 * Method to recover the fields of a given metric
	 * @param metric - given metric object
	 * @return the list of class fields
	 */
	public List<Field> getMetricFields(Object metric) {
		List<Field> fields = new ArrayList<Field>();		
		//Gets the class of the metric
		Class<?> clazz = metric.getClass();		
		//Gets fields from the class
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		return fields;
	}//getMetricFields()
	
	/**
	 * Method for mounting SQL table creation
	 * @param metric - given metric object
	 * @param fields
	 * @return a create table SQL ready
	 */
	public String makeCreateTableSQL(Object metric, List<Field> fields) {
		String clazzName = metric.getClass().getSimpleName().toLowerCase();
		StringBuilder sql = new StringBuilder();
		//Initiates the formation of query
		sql.append("create table `"+clazzName+"_metric` (\n")
		   .append("`id` int(11) NOT NULL AUTO_INCREMENT,\n")
		   .append("`record_date` datetime NOT NULL,\n");
		//For each field of the metric is verified its type and converted to SQL type
		for (Field field : fields) {
			if (field.getName().toLowerCase().contains(clazzName)) {
				sql.append("`"+field.getName().toLowerCase().replaceAll(clazzName, "")+"` "+TypeTranslater.getSQLType(field.getType())+" DEFAULT NULL,\n");
			}			
		}				
		//Identifies the metric is tied to a machine or database or host
		if (metric.toString().equals("machine")) {
			//Add the identifier of the resource and primary key
			sql.append("`identifier` int(11) NOT NULL,\n")
			   .append("PRIMARY KEY (`id`),\n")
			   .append("KEY `fk_"+clazzName+"_metric_idx` (`identifier`),\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_machine` FOREIGN KEY (`identifier`) REFERENCES `virtual_machine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		} else if (metric.toString().equals("host")) {
			//Add the identifier of the resource and primary key
			sql.append("`identifier` int(11) NOT NULL,\n")
			   .append("PRIMARY KEY (`id`),\n")
			   .append("KEY `fk_"+clazzName+"_metric_idx` (`identifier`),\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_host` FOREIGN KEY (`identifier`) REFERENCES `host` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		} else {
			//Add the identifier of the resource and primary key
			sql.append("`dbms` int(11) DEFAULT NULL,\n")
			   .append("`database` int(11) DEFAULT NULL,\n")
			   .append("PRIMARY KEY (`id`),\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_dbms` FOREIGN KEY (`dbms`) REFERENCES `dbms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_database` FOREIGN KEY (`database`) REFERENCES `database` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		}
		sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8");
		return sql.toString();
	}
	
	/**
	 * Method for mounting SQL insertion
	 * @param metric - given metric object
	 * @param fields
	 * @return a insert SQL ready
	 */
	public String makeInsertSQL(Object metric, List<Field> fields, int dbms, int database) {
		String clazzName = metric.getClass().getSimpleName().toLowerCase();
		//Create the first part of the query
		StringBuilder insert = new StringBuilder();		
		insert.append("insert into `"+clazzName+"_metric` (");
		//Create the second part of the query
		StringBuilder values = new StringBuilder();
		values.append("values (");
		//For each field of the metric is added a field and a parameter in the query
		for (Field field : fields) {
			if (field.getName().toLowerCase().contains(clazzName)) {
				insert.append("`"+field.getName().toLowerCase().replaceAll(clazzName, "")+"`, ");
				values.append("?, ");
			}
		}
		//Adds the end of the query
		if (dbms != 0) {
			insert.append("`dbms`, ");
		} else if (database != 0) {
			insert.append("`database`, ");
		} else {
			insert.append("`identifier`, ");
		}		
		insert.append("`record_date`)\n");
		values.append("?, ")
	          .append("?);");
		//Concat first and second part
		insert.append(values);
		
		return insert.toString();
	}
	
	/**
	 * Method that checks if there is the table of a given metric.	
	 * @param metric - given metric object
	 * @return whether the table exists or not
	 */
	public boolean checkTable(Object metric) {
		String clazzName = metric.getClass().getSimpleName().toLowerCase();
		for (String table : this.tables) {
			if (table.equals(clazzName+"_metric")) {
				return true;
			}
		}
		return false;
	}//checkTable()
	
	/**
	 * Method to return all tables in the database.
	 * @return the list of tables
	 */
	public List<String> getTables() {
		List<String> tables = new ArrayList<String>();
		try {
			connection = this.databasePool.getConnection();
			preparedStatement = connection.prepareStatement("select table_name from information_schema.tables where table_schema = database();");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				tables.add(resultSet.getString("table_name"));
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
	}//getTables()
}