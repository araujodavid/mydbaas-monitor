package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;

/**
 * @author David Araújo - @araujodavid
 * @version 3.0
 * @since May 8, 2013
 */

@Component
public class HostRepository implements GenericRepository<Host> {
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

	@Override
	public List<Host> all() {
		ArrayList<Host> hostList = new ArrayList<Host>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `host` order by `id`;");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Host host = getEntity(resultSet);
				hostList.add(host);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return hostList;
	}//all()
	
	public List<Host> getDBaaSHosts(int dbaasId) {
		ArrayList<Host> hostList = new ArrayList<Host>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from virtual_machine where `dbaas` = ? order by `id`;");
			
			preparedStatement.setInt(1, dbaasId);
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Host host = getEntity(resultSet);
				hostList.add(host);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return hostList;
	} //getDBaaSHosts()

	@Override
	public Host find(Integer id) {
		Host host = null;
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `host` where `id` = ?;");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				host = getEntity(resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return host;
	}//find()

	@Override
	public void remove(Host resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Host resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into host " +
					"(`record_date`, `alias`, `description`, `address`, `port`, `username`, `password`, `status`, `dbaas`) " +
					"values (now(), ?, ?, ?, ?, ?, ?, false, ?);");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getAddress());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getUser());
			this.preparedStatement.setString(6, resource.getPassword());
			this.preparedStatement.setInt(7, resource.getEnvironment().getId());
			
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
	public void update(Host resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update host " +
					"set `alias` = ?, `description` = ?, `address` = ?, `port` = ?, `username` = ?, `status` = ?, `dbaas` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getDescription());
			this.preparedStatement.setString(3, resource.getAddress());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getUser());			
			this.preparedStatement.setBoolean(6, resource.getStatus());
			this.preparedStatement.setInt(7, resource.getEnvironment().getId());
			this.preparedStatement.setInt(8, resource.getId());
			
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
	
	public void updatePassword(Host resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update host " +
					"set `password` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getPassword());
			this.preparedStatement.setInt(2, resource.getId());
			
			this.preparedStatement.executeUpdate();
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
			try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
		}
	}//updatePassword()
	
	@Override
	public Host getEntity(ResultSet resultSet) throws SQLException {
		Host host = new Host();
		DBaaS dBaaS = new DBaaS();
		host.setId(resultSet.getInt("id"));
		host.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		host.setAlias(resultSet.getString("alias"));
		host.setDescription(resultSet.getString("description"));
		host.setAddress(resultSet.getString("address"));
		host.setPort(resultSet.getInt("port"));
		host.setUser(resultSet.getString("username"));
		host.setPassword(resultSet.getString("password"));
		host.setStatus(resultSet.getBoolean("status"));
		dBaaS.setId(resultSet.getInt("dbaas"));
		host.setEnvironment(dBaaS);
		return host;
	}

}
