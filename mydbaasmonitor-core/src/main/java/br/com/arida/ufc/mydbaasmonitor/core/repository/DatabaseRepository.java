package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo
 * @version 1.0
 * @since February 27, 2013 
 */

@Component
public class DatabaseRepository implements GenericRepository<Database>{

	@Override
	public List<Database> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Database find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Database entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Database entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Database entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Database getEntity(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}	
}
