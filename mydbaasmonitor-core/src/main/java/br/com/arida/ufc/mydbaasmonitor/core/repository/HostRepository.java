package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.HostInfo;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.util.DataUtil;

/**
 * @author David Araújo - @araujodavid
 * @version 4.0
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
			preparedStatement = connection.prepareStatement("select * from `host` where `dbaas` = ? order by `id`;");
			
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
	
	public boolean updateHostInformation(HostInfo hostInfo, int hostId) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update `host` " +
					"set `host_name` = ?, `host_hypervisor` = ?, `host_cores` = ?, `host_cpus` = ?, `host_memory` = ?, `host_mhz` = ?, " +
					"`host_model` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, hostInfo.getHostInfoName());
			this.preparedStatement.setString(2, hostInfo.getHostInfoHypervisor());
			this.preparedStatement.setInt(3, hostInfo.getHostInfoCores());
			this.preparedStatement.setInt(4, hostInfo.getHostInfoCpus());
			this.preparedStatement.setDouble(5, hostInfo.getHostInfoMemory());
			this.preparedStatement.setDouble(6, hostInfo.getHostInfoMhz());
			this.preparedStatement.setString(7, hostInfo.getHostInfoModel());
			this.preparedStatement.setInt(8, hostId);
			
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
	}//updateHostInformation()
	
	@Override
	public Host getEntity(ResultSet resultSet) throws SQLException {
		Host host = new Host();
		HostInfo hostInfo = new HostInfo();
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
		hostInfo.setHostInfoName(resultSet.getString("host_name"));
		hostInfo.setHostInfoHypervisor(resultSet.getString("host_hypervisor"));
		hostInfo.setHostInfoCores(resultSet.getInt("host_cores"));
		hostInfo.setHostInfoCpus(resultSet.getInt("host_cpus"));
		hostInfo.setHostInfoMemory(resultSet.getDouble("host_memory"));
		hostInfo.setHostInfoMhz(resultSet.getDouble("host_mhz"));
		hostInfo.setHostInfoModel(resultSet.getString("host_model"));
		host.setEnvironment(dBaaS);
		host.setInformation(hostInfo);
		return host;
	}

}
