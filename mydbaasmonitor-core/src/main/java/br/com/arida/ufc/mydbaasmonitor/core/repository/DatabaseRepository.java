package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since February 27, 2013 
 */

@Component
public class DatabaseRepository implements GenericRepository<Database> {
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
	@Override
	public List<Database> all() {
		ArrayList<Database> databaseList = new ArrayList<Database>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `database` order by `id`;");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Database database = getEntity(resultSet);
				databaseList.add(database);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }		
		return databaseList;
	}//all()
	
	public List<Database> getDBMSDatabases(int dbmsId) {
		ArrayList<Database> databases = new ArrayList<Database>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `database` where `dbms` = ? order by `id`;");
			
			preparedStatement.setInt(1, dbmsId);
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Database database = getEntity(resultSet);
				databases.add(database);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return databases;
	}//getDBMSDatabases()
	
	@Override
	public Database find(Integer id) {
		Database database = null;
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `database` where `id` = ?;");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				database = getEntity(resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return database;
	}//find()
	
	@Override
	public void remove(Database resource) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(Database resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into `database` " +
					"(`alias`, `record_date`, `description`, `name`, `status`, `dbms`) " +
					"values (?, now(), ?, ?, false, ?);");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getName());
			this.preparedStatement.setInt(4, resource.getDbms().getId());
			
			this.preparedStatement.executeUpdate();
		} 
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
        }
	}//save()
	
	@Override
	public void update(Database resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update `database` " +
					"set `alias` = ?, `description` = ?, `name` = ?, `status` = ?, `dbms` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getName());
			this.preparedStatement.setBoolean(4, resource.getStatus());
			this.preparedStatement.setInt(5, resource.getDbms().getId());
			this.preparedStatement.setInt(6, resource.getId());
			
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
			try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
		}
	}//update()
	
	@Override
	public Database getEntity(ResultSet resultSet) throws SQLException {
		Database database = new Database();
		DBMS dbms = new DBMS();
		database.setId(resultSet.getInt("id"));
		database.setAlias(resultSet.getString("alias"));
		database.setRecordDate(DataUtil.converteDateParaString(resultSet.getTimestamp("record_date")));
		database.setDescription(resultSet.getString("description"));
		database.setName(resultSet.getString("name"));
		database.setStatus(resultSet.getBoolean("status"));
		dbms.setId(resultSet.getInt("dbms"));
		database.setDbms(dbms);
		return database;
	}
	
}
