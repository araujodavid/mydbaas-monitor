package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import br.com.caelum.vraptor.ioc.Component;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Host;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 8, 2013
 */

@Component
public class HostRepository implements GenericRepository<Host> {

	@Override
	public List<Host> all() {
		// TODO Auto-generated method stub
		return null;
	}

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
		// TODO Auto-generated method stub
		return null;
	}

}
