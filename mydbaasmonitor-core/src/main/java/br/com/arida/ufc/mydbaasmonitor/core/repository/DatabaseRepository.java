package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 1.0
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
	
	public List<Database> list(VirtualMachine virtualMachine) {
		ArrayList<Database> databaseList = new ArrayList<Database>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `database` where `virtual_machine` = ? order by `id`;");
			
			this.preparedStatement.setInt(1, virtualMachine.getId());
			
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
	}//list()

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
	} //find()

	@Override
	public void remove(Database entity) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void save(Database entity) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into `database` " +
					"(`username`, `port`, `password`, `record_date`, `description`, `alias`, `status`, `type`, `virtual_machine`) " +
					"values (?, ?, ?, now(), ?, ?, false, ?, ?);");
			
			this.preparedStatement.setString(1, entity.getUser());
			this.preparedStatement.setInt(2, entity.getPort());
			this.preparedStatement.setString(3, entity.getPassword());
			this.preparedStatement.setString(4, entity.getDescription());
			this.preparedStatement.setString(5, entity.getAlias());
			this.preparedStatement.setString(6, entity.getType());
			this.preparedStatement.setInt(7, entity.getVirtualMachine().getId());
			
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
	public void update(Database entity) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update `database` " +
					"set `username` = ?, `port` = ?, `password` = ?, `alias` = ?, `status` = ?, `type` = ?, `description` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, entity.getUser());
			this.preparedStatement.setInt(2, entity.getPort());
			this.preparedStatement.setString(3, entity.getPassword());
			this.preparedStatement.setString(4, entity.getAlias());
			this.preparedStatement.setBoolean(5, entity.getStatus());
			this.preparedStatement.setString(6, entity.getType());
			this.preparedStatement.setString(7, entity.getDescription());
			this.preparedStatement.setInt(8, entity.getId());
			
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
		VirtualMachine virtualMachine = new VirtualMachine();
		database.setId(resultSet.getInt("id"));
		database.setAlias(resultSet.getString("alias"));
		database.setDescription(resultSet.getString("description"));
		database.setPassword(resultSet.getString("description"));
		database.setPort(resultSet.getInt("port"));
		database.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		database.setStatus(resultSet.getBoolean("status"));
		database.setType(resultSet.getString("type"));
		database.setUser(resultSet.getString("username"));
		virtualMachine.setId(resultSet.getInt("virtual_machine"));
		database.setVirtualMachine(virtualMachine);
		return database;
	}	
}
