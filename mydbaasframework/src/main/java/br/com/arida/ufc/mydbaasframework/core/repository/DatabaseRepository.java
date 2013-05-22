package main.java.br.com.arida.ufc.mydbaasframework.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.Database;
import main.java.br.com.arida.ufc.mydbaasframework.core.repository.common.GenericRepository;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013 
 */

public abstract class DatabaseRepository implements GenericRepository<Database> {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public abstract List<Database> getDBMSDatabases(int dbmsId);
}
