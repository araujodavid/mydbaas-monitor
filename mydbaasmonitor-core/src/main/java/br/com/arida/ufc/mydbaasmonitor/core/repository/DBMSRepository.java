package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 6, 2013 
 */

@Component
public class DBMSRepository implements GenericRepository<DBMS> {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private DatabaseRepository databaseRepository;
	
	@Override
	public List<DBMS> all() {
		ArrayList<DBMS> dbmsList = new ArrayList<DBMS>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `dbms` order by `id`;");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DBMS dbms = getEntity(resultSet);
				dbmsList.add(dbms);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return dbmsList;
	}//all()
	
	public List<DBMS> list(VirtualMachine virtualMachine) {
		ArrayList<DBMS> dbmsList = new ArrayList<DBMS>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `dbms` where `virtual_machine` = ? order by `id`;");
			
			this.preparedStatement.setInt(1, virtualMachine.getId());
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				DBMS dbms = getEntity(resultSet);
				dbmsList.add(dbms);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }		
		return dbmsList;
	}//list()

	@Override
	public DBMS find(Integer id) {
		DBMS dbms = null;
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `dbms` where `id` = ?;");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				dbms = getEntity(resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return dbms;
	}//find()

	@Override
	public void remove(DBMS resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(DBMS resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into `dbms` " +
					"(`alias`, `record_date`, `description`, `host`, `port`, `username`, `password`, `status`, `type`, `virtual_machine`) " +
					"values (?, now(), ?, ?, ?, ?, ?, false, ?, ?);");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getHost());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getUser());
			this.preparedStatement.setString(6, resource.getPassword());
			this.preparedStatement.setString(7, resource.getType());
			this.preparedStatement.setInt(8, resource.getMachine().getId());
			
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
	public void update(DBMS resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update `dbms` " +
					"set `alias` = ?, `description` = ?, `host` = ?, `port` = ?, `username` = ?, `password` = ?, `status` = ?, `type` = ?, `virtual_machine` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getHost());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getUser());
			this.preparedStatement.setString(6, resource.getPassword());
			this.preparedStatement.setBoolean(7, resource.getStatus());
			this.preparedStatement.setString(8, resource.getType());
			this.preparedStatement.setInt(9, resource.getMachine().getId());
			this.preparedStatement.setInt(10, resource.getId());
			
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
	public DBMS getEntity(ResultSet resultSet) throws SQLException {
		this.databaseRepository = new DatabaseRepository();
		DBMS dbms = new DBMS();
		VirtualMachine virtualMachine = new VirtualMachine();
		dbms.setId(resultSet.getInt("id"));
		dbms.setAlias(resultSet.getString("alias"));
		dbms.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		dbms.setDescription(resultSet.getString("description"));
		dbms.setHost(resultSet.getString("host"));
		dbms.setPort(resultSet.getInt("port"));
		dbms.setUser(resultSet.getString("username"));
		dbms.setPassword(resultSet.getString("password"));
		dbms.setStatus(resultSet.getBoolean("status"));
		dbms.setType(resultSet.getString("type"));
		virtualMachine.setId(resultSet.getInt("virtual_machine"));
		dbms.setMachine(virtualMachine);
		dbms.setDatabases(this.databaseRepository.getDBMSDatabases(dbms));
		return dbms;
	}

}
