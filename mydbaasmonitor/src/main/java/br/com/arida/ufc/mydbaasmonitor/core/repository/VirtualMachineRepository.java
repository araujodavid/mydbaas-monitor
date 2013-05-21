package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Machine;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;
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
public class VirtualMachineRepository implements GenericRepository<VirtualMachine>{
	
	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

	@Override
	public List<VirtualMachine> all() {
		ArrayList<VirtualMachine> virtualMachinesList = new ArrayList<VirtualMachine>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from virtual_machine order by `id`;");
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				VirtualMachine virtualMachine = getEntity(resultSet);
				virtualMachinesList.add(virtualMachine);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return virtualMachinesList;
	}//all()
	
	public List<VirtualMachine> getDBaaSMachines(int dbaasId) {
		ArrayList<VirtualMachine> virtualMachinesList = new ArrayList<VirtualMachine>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from virtual_machine where `dbaas` = ? order by `id`;");
			
			preparedStatement.setInt(1, dbaasId);
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				VirtualMachine virtualMachine = getEntity(resultSet);
				virtualMachinesList.add(virtualMachine);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return virtualMachinesList;
	} //getDBaaSMachines()
	
	public List<VirtualMachine> getHostMachines(int hostId) {
		ArrayList<VirtualMachine> machines = new ArrayList<VirtualMachine>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from `virtual_machine` where `host` = ? order by `id`;");
			
			preparedStatement.setInt(1, hostId);
						
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				VirtualMachine virtualMachine = getEntity(resultSet);
				machines.add(virtualMachine);
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		
		return machines;
	}//getHostMachines()

	@Override
	public VirtualMachine find(Integer id) {
		VirtualMachine virtualMachine = null;
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from virtual_machine where `id` = ?;");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				virtualMachine = getEntity(resultSet);	
			}
		}
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) {}
            try { preparedStatement.close(); } catch(Exception e) {}
            try { connection.close(); } catch(Exception e) {}
        }
		return virtualMachine;
	} //find()

	@Override
	public void remove(VirtualMachine resource) {
		// TODO Auto-generated method stub		
	}//remove()

	@Override
	public void save(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into virtual_machine " +
					"(`address`, `username`, `port`, `password`, `record_date`, `key`, `description`, `alias`, `status`, `dbaas`, `host`, `identifier_host`) " +
					"values (?, ?, ?, ?, now(), ?, ?, ?, false, ?, ?, ?);");
			
			this.preparedStatement.setString(1, resource.getAddress());
			this.preparedStatement.setString(2, resource.getUser());
			this.preparedStatement.setInt(3, resource.getPort());
			this.preparedStatement.setString(4, resource.getPassword());
			this.preparedStatement.setString(5, resource.getKey());
			this.preparedStatement.setString(6, resource.getDescription());
			this.preparedStatement.setString(7, resource.getAlias());
			this.preparedStatement.setInt(8, resource.getEnvironment().getId());
			this.preparedStatement.setInt(9, resource.getHost().getId());
			this.preparedStatement.setString(10, resource.getIdentifierHost());
			
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
	
	public void saveWithoutHost(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into virtual_machine " +
					"(`address`, `username`, `port`, `password`, `record_date`, `key`, `description`, `alias`, `status`, `dbaas`) " +
					"values (?, ?, ?, ?, now(), ?, ?, ?, false, ?);");
			
			this.preparedStatement.setString(1, resource.getAddress());
			this.preparedStatement.setString(2, resource.getUser());
			this.preparedStatement.setInt(3, resource.getPort());
			this.preparedStatement.setString(4, resource.getPassword());
			this.preparedStatement.setString(5, resource.getKey());
			this.preparedStatement.setString(6, resource.getDescription());
			this.preparedStatement.setString(7, resource.getAlias());
			this.preparedStatement.setInt(8, resource.getEnvironment().getId());
			
			this.preparedStatement.executeUpdate();
		} 
		catch(SQLException se) {se.printStackTrace();}
		catch (RuntimeException re) {re.printStackTrace();}
		finally {
            try { resultSet.close(); } catch(Exception e) { }
            try { preparedStatement.close(); } catch(Exception e) { }
            try { connection.close(); } catch(Exception e) { }
        }
	}//saveWithoutHost()

	@Override
	public void update(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update virtual_machine " +
					"set `alias` = ?, `address` = ?, `username` = ?, `port` = ?, `description` = ?, `status` = ?, `dbaas` = ?, `host` = ? , `identifier_host` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getAddress());
			this.preparedStatement.setString(3, resource.getUser());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getDescription());
			this.preparedStatement.setBoolean(6, resource.getStatus());
			this.preparedStatement.setInt(7, resource.getEnvironment().getId());
			this.preparedStatement.setInt(8, resource.getHost().getId());
			this.preparedStatement.setString(9, resource.getIdentifierHost());
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
	
	public void updateWithoutHost(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update virtual_machine " +
					"set `alias` = ?, `address` = ?, `username` = ?, `port` = ?, `description` = ?, `status` = ?, `dbaas` = ?, `host` = null " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getAddress());
			this.preparedStatement.setString(3, resource.getUser());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getDescription());
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
	}//updateWithoutHost()
	
	public boolean updateSystemInformation(Machine system, int machine) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update virtual_machine " +
					"set `so` = ?, `kernel_name` = ?, `kernel_version` = ?, `architecture` = ?, `memory` = ?, `swap` = ?, " +
					"`cpu_cores` = ?, `cpu_sockets` = ?, `cores_sockets` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, system.getMachineOperatingSystem());
			this.preparedStatement.setString(2, system.getMachineKernelName());
			this.preparedStatement.setString(3, system.getMachineKernelVersion());
			this.preparedStatement.setString(4, system.getMachineArchitecture());
			this.preparedStatement.setLong(5, system.getMachineTotalMemory());
			this.preparedStatement.setLong(6, system.getMachineTotalSwap());
			this.preparedStatement.setInt(7, system.getMachineTotalCPUCores());
			this.preparedStatement.setInt(8, system.getMachineTotalCPUSockets());
			this.preparedStatement.setInt(9, system.getMachineTotalCoresPerSocket());
			this.preparedStatement.setInt(10, machine);
			
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
	}//updateSystemInformation()
	
	public void updatePassword(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update virtual_machine " +
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
	
	/**
	 * Method to convert a ResultSet object in an VirtualMachine object.
	 * @param resultSet
	 * @return virtualMachine
	 */
	@Override
	public VirtualMachine getEntity(ResultSet resultSet) throws SQLException {
		VirtualMachine virtualMachine = new VirtualMachine();
		Machine machine = new Machine();
		DBaaS environment = new DBaaS();
		Host host = new Host();
		virtualMachine.setId(resultSet.getInt("id"));
		virtualMachine.setAddress(resultSet.getString("address"));
		virtualMachine.setPort(resultSet.getInt("port"));
		virtualMachine.setUser(resultSet.getString("username"));
		virtualMachine.setPassword(resultSet.getString("password"));
		virtualMachine.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		virtualMachine.setDescription(resultSet.getString("description"));
		virtualMachine.setAlias(resultSet.getString("alias"));
		virtualMachine.setStatus(resultSet.getBoolean("status"));
		virtualMachine.setKey(resultSet.getString("key"));
		virtualMachine.setIdentifierHost(resultSet.getString("identifier_host"));
		machine.setMachineOperatingSystem(resultSet.getString("so"));
		machine.setMachineKernelName(resultSet.getString("kernel_name"));
		machine.setMachineKernelVersion(resultSet.getString("kernel_version"));
		machine.setMachineArchitecture(resultSet.getString("architecture"));
		machine.setMachineTotalMemory(resultSet.getLong("memory"));
		machine.setMachineTotalSwap(resultSet.getLong("swap"));
		machine.setMachineTotalCPUCores(resultSet.getInt("cpu_cores"));
		machine.setMachineTotalCPUSockets(resultSet.getInt("cpu_sockets"));
		machine.setMachineTotalCoresPerSocket(resultSet.getInt("cores_sockets"));
		environment.setId(resultSet.getInt("dbaas"));
		host.setId(resultSet.getInt("host"));
		virtualMachine.setInformation(machine);
		virtualMachine.setEnvironment(environment);		
		virtualMachine.setHost(host);
		
		return virtualMachine;
	}//getEntity()
}
