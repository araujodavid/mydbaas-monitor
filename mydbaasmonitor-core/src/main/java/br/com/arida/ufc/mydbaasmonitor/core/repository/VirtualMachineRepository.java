package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import main.java.br.com.arida.ufc.mydbaasmonitor.util.DataUtil;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 1.0
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
	
	public List<VirtualMachine> list(boolean status) {
		ArrayList<VirtualMachine> virtualMachinesList = new ArrayList<VirtualMachine>();
		try {
			connection = Pool.getConnection(Pool.JDBC_MySQL);
			preparedStatement = connection.prepareStatement("select * from virtual_machine where `status` = ? order by `id`;");
			
			preparedStatement.setBoolean(1, status);
						
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
	} //list()

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
					"(`host`, `username`, `port`, `password`, `record_date`, `key`, `description`, `alias`, `status`) " +
					"values (?, ?, ?, ?, now(), ?, ?, ?, true);");
			
			this.preparedStatement.setString(1, resource.getHost());
			this.preparedStatement.setString(2, resource.getUser());
			this.preparedStatement.setInt(3, resource.getPort());
			this.preparedStatement.setString(4, resource.getPassword());
			this.preparedStatement.setString(5, resource.getKey());
			this.preparedStatement.setString(6, resource.getDescription());
			this.preparedStatement.setString(7, resource.getAlias());
			
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
	public void update(VirtualMachine resource) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"update virtual_machine " +
					"set `alias` = ?, `host` = ?, `username` = ?, `port` = ?, `description` = ?, `status` = ? " +
					"where `id` = ?;");
			
			this.preparedStatement.setString(1, resource.getAlias());
			this.preparedStatement.setString(2, resource.getHost());
			this.preparedStatement.setString(3, resource.getUser());
			this.preparedStatement.setInt(4, resource.getPort());
			this.preparedStatement.setString(5, resource.getDescription());
			this.preparedStatement.setBoolean(6, resource.getStatus());
			this.preparedStatement.setInt(7, resource.getId());
			
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
	 * @author David Araújo
	 * @param resultSet
	 * @return virtualMachine
	 */
	@Override
	public VirtualMachine getEntity(ResultSet resultSet) throws SQLException {
		VirtualMachine virtualMachine = new VirtualMachine();
		
		virtualMachine.setId(resultSet.getInt("id"));
		virtualMachine.setHost(resultSet.getString("host"));
		virtualMachine.setPort(resultSet.getInt("port"));
		virtualMachine.setUser(resultSet.getString("username"));
		virtualMachine.setPassword(resultSet.getString("password"));
		virtualMachine.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		virtualMachine.setDescription(resultSet.getString("description"));
		virtualMachine.setAlias(resultSet.getString("alias"));
		virtualMachine.setMemory(resultSet.getString("ram"));
		virtualMachine.setOperatingSystem(resultSet.getString("so"));
		virtualMachine.setKernel(resultSet.getString("kernel"));
		virtualMachine.setSwap(resultSet.getString("swap"));
		virtualMachine.setCores(resultSet.getInt("cores"));
		virtualMachine.setStatus(resultSet.getBoolean("status"));
		
		return virtualMachine;
	}//getEntity()
}
