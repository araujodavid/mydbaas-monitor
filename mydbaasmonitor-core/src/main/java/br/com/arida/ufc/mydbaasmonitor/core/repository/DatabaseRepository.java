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
		// TODO Auto-generated method stub
		return null;
	}
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void remove(Database resource) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save(Database resource) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Database resource) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Database getEntity(ResultSet resultSet) throws SQLException {
		Database database = new Database();
		DBMS dbms = new DBMS();
		database.setId(resultSet.getInt("id"));
		database.setAlias(resultSet.getString("alias"));
		database.setRecordDate(DataUtil.converteDateParaString(resultSet.getTimestamp("record_date")));
		database.setDescription(resultSet.getString("description"));
		database.setName(resultSet.getString("name"));
		dbms.setId(resultSet.getInt("dbms"));
		database.setDbms(dbms);
		return database;
	}
	
}
