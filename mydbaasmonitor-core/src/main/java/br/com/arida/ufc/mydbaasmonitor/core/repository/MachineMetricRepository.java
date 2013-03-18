package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.Memory;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.connection.Pool;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 2.0
 * @since March 11, 2013 
 */

@Component
public class MachineMetricRepository {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
	public boolean saveCpuMetric(Cpu cpu, int machine, String recordDate) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into cpu_metric " +
					"(`virtual_machine`, `cpu_user`, `cpu_system`, `cpu_nice`, `cpu_wait`, `cpu_idle`, `cpu_combined`, `record_date`) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?);");
			
			this.preparedStatement.setInt(1, machine);
			this.preparedStatement.setDouble(2, cpu.getCpuUser());
			this.preparedStatement.setDouble(3, cpu.getCpuSystem());
			this.preparedStatement.setDouble(4, cpu.getCpuNice());
			this.preparedStatement.setDouble(5, cpu.getCpuWait());
			this.preparedStatement.setDouble(6, cpu.getCpuIdle());
			this.preparedStatement.setDouble(7, cpu.getCpuCombined());
			this.preparedStatement.setString(8, recordDate);
			
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
	}//saveCpuMetric()
	
	public boolean saveMemoryMetric(Memory memory, int machine, String recordDate) {
		try {
			this.connection = Pool.getConnection(Pool.JDBC_MySQL);
			this.preparedStatement = this.connection.prepareStatement(
					"insert into cpu_metric " +
					"(`virtual_machine`, `swap_used`, `swap_free`, `memory_used`, `memory_free`, `memory_used_percent`, `memory_free_percent`, `buffers_cache_used`, `buffers_cache_free`, `record_date`) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			this.preparedStatement.setInt(1, machine);
			this.preparedStatement.setLong(2, memory.getSwapUsed());
			this.preparedStatement.setLong(3, memory.getSwapFree());
			this.preparedStatement.setLong(4, memory.getMemoryUsed());
			this.preparedStatement.setLong(5, memory.getMemoryFree());
			this.preparedStatement.setDouble(6, memory.getMemoryUsedPercent());
			this.preparedStatement.setDouble(7, memory.getMemoryFreePercent());
			this.preparedStatement.setLong(8, memory.getBuffersCacheUsed());
			this.preparedStatement.setLong(9, memory.getBuffersCacheFree());
			this.preparedStatement.setString(10, recordDate);
			
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
	}//saveMemoryMetric()
}
