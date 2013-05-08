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
 * @version 2.0
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

	@Override
	public Host find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Host resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Host resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Host resource) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Host getEntity(ResultSet resultSet) throws SQLException {
		Host host = new Host();
		DBaaS dBaaS = new DBaaS();
		host.setId(resultSet.getInt("id"));
		host.setRecordDate(DataUtil.converteDateParaString(resultSet.getDate("record_date")));
		host.setAlias(resultSet.getString("alias"));
		host.setDescription(resultSet.getString("description"));
		host.setHost(resultSet.getString("host"));
		host.setPort(resultSet.getInt("port"));
		host.setUser(resultSet.getString("username"));
		host.setPassword(resultSet.getString("password"));
		host.setStatus(resultSet.getBoolean("status"));
		dBaaS.setId(resultSet.getInt("dbaas"));
		host.setEnvironment(dBaaS);
		return host;
	}

}
