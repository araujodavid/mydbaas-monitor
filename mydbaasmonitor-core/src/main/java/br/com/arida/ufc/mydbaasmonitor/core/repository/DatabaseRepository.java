package main.java.br.com.arida.ufc.mydbaasmonitor.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.common.GenericRepository;
import br.com.caelum.vraptor.ioc.Component;

/**
 * @author David Araújo - @araujodavid
 * @version 3.0
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
	@Override
	public Database find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void remove(Database resource) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save(Database resource) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Database resource) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Database getEntity(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
