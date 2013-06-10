package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.sun.xml.internal.ws.util.StringUtils;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.common.AbstractMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.TypeTranslater;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 4.0
 * @since April 1, 2013 
 */

@Component
public class MetricRepository {
	
	public static final int ALL_COLLECTION = 0;
	public static final int LAST_COLLECTION = 1;
	
	public static final int METRIC_SINGLE_TYPE = 0;
	public static final int METRIC_MULTI_TYPE = 1;
	public static final int METRIC_NO_TYPE = 2;
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private List<String> tables = null;
    
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
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
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
			connection = Pool.getConnection(Pool.JDBC_MySQL);
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
	 * Method to recover the fields of a given metric object
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
	 * Method to recover the fields of a given metric class
	 * @param metricClass - given metric class
	 * @return the list of class fields
	 */
	public List<String> getMetricTableFields(Class<?> metricClass) {
		List<String> tableFields = new ArrayList<String>();	
		for (Field metricField : metricClass.getDeclaredFields()) {
			tableFields.add(metricField.getName().toLowerCase().replaceAll(metricClass.getSimpleName().toLowerCase(), ""));
		}		
		return tableFields;
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
			   .append("KEY `idx_date_"+clazzName+"_metric` (`record_date`),\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_machine` FOREIGN KEY (`identifier`) REFERENCES `virtual_machine` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		} else if (metric.toString().equals("host")) {
			//Add the identifier of the resource and primary key
			sql.append("`identifier` int(11) NOT NULL,\n")
			   .append("PRIMARY KEY (`id`),\n")
			   .append("KEY `fk_"+clazzName+"_metric_idx` (`identifier`),\n")
			   .append("KEY `idx_date_"+clazzName+"_metric` (`record_date`),\n")
			   .append("CONSTRAINT `fk_"+clazzName+"_metric_host` FOREIGN KEY (`identifier`) REFERENCES `host` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n");
		} else {
			//Add the identifier of the resource and primary key
			sql.append("`dbms` int(11) DEFAULT NULL,\n")
			   .append("`database` int(11) DEFAULT NULL,\n")
			   .append("PRIMARY KEY (`id`),\n")
			   .append("KEY `fk_"+clazzName+"_dbms_idx` (`dbms`),\n")			   
			   .append("KEY `fk_"+clazzName+"_database_idx` (`identifier`),\n")
			   .append("KEY `idx_date_"+clazzName+"_metric` (`database`),\n")
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
			connection = Pool.getConnection(Pool.JDBC_MySQL);
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
	
	/**
	 * Method to perform queries that return only one tuple.
	 * @param sql
	 * @param metricClass
	 * @return an filled object of a given metric
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public Object queryMetric(String sql, Class<?> metricClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Object objectMetric = null;
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				objectMetric = getMetricEntity(metricClass, resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return metricClass.cast(objectMetric);
	}//findMetric()
	
	/**
	 * Method to perform queries that return multiple tuples.
	 * @param sql
	 * @param metricClass
	 * @return returns a list of filled objects of a given metric
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public List<Object> queryMetrics(String sql, Class<?> metricClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		ArrayList<Object> listMetrics = new ArrayList<Object>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement(sql);
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Object objectMetric = getMetricEntity(metricClass, resultSet);
				listMetrics.add(metricClass.cast(objectMetric));
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return listMetrics;
	}//listMetrics()
	
	public String makeQuerySQL(Class<?> metricClass, int metricType, String resourceType, int resourceID, int queryType, String startDatetime, String endDatetime) {
		StringBuilder sql = new StringBuilder();
		String metricTable = metricClass.getSimpleName().toLowerCase()+"_metric";
		
		//Add the table name into query
		sql.append("select * from `"+metricTable+"`\n");
		
		//Add the owner of the metric
		String owner;
		switch (resourceType) {
		case "dbms":
			owner = "dbms";
			sql.append("where dbms = "+resourceID+"\n");
			break;
		case "database":
			owner = "database";
			sql.append("where database = "+resourceID+"\n");
			break;
		default:
			owner = "identifier";
			sql.append("where identifier = "+resourceID+"\n");
			break;
		}
		
		//Checks the query type
		switch (queryType) {
		//All collection
		case 0:
			//Adds part of the query based on the combination of start and end dates
			if ((startDatetime != null && !startDatetime.trim().isEmpty()) && (endDatetime != null && !endDatetime.trim().isEmpty())) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') between '"+startDatetime+"' and '"+endDatetime+"'\n");
			} else if ((startDatetime != null && !startDatetime.trim().isEmpty()) && endDatetime == null) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') >= '"+startDatetime+"'\n");
			} else if (startDatetime == null && (endDatetime != null && !endDatetime.trim().isEmpty())) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') <= '"+endDatetime+"'\n");
			}
			sql.append("order by id;");
			break;
		//Last collection
		case 1:
			//Adds part of the query based on the combination of start and end dates
			if ((startDatetime != null && !startDatetime.trim().isEmpty()) && (endDatetime != null && !endDatetime.trim().isEmpty())) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') between '"+startDatetime+"' and '"+endDatetime+"'\n");
			} else if ((startDatetime != null && !startDatetime.trim().isEmpty()) && endDatetime == null) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') > '"+startDatetime+"'\n");
			} else if (startDatetime == null && (endDatetime != null && !endDatetime.trim().isEmpty())) {
				sql.append("and date_format(record_date, '%d-%m-%Y %T') < '"+endDatetime+"'\n");
			}
			if (metricType == 1) {
				sql.append("and record_date = (select max(record_date) from "+metricTable+" where "+owner+" = "+resourceID+");");
			} else {
				sql.append("order by id desc\n")
				   .append("limit 1;");
			}			
			break;
		}
		return sql.toString();
	}
	
	/**
	 * Method that takes a class of metric and a resultset and returns an object of this metric with the fields filled in accordance with the resultset.
	 * @param metricClass
	 * @param resultSet
	 * @return an filled object of a given metric
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object getMetricEntity(Class<?> metricClass, ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Object metric = metricClass.newInstance();
		List<Field> fields = this.getMetricFields(metric);
		for (Field field : fields) {
			Method method;
			if (field.getName().toLowerCase().contains(metricClass.getSimpleName().toLowerCase())) {
				method = metricClass.getDeclaredMethod("set"+StringUtils.capitalize(field.getName()), field.getType());
				method.invoke(metric, resultSet.getObject(field.getName().toLowerCase().replaceAll(metricClass.getSimpleName().toLowerCase(), "")));
			}			
		}
		Method methodSetRecordDate = AbstractMetric.class.getDeclaredMethod("setRecordDate", String.class);
		methodSetRecordDate.invoke(metric, DataUtil.convertDateToStringAPI(resultSet.getTimestamp("record_date")));
		return metric;
	}
}











