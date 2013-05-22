package main.java.br.com.arida.ufc.mydbaasframework.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.DBaaS;
import main.java.br.com.arida.ufc.mydbaasframework.core.repository.common.GenericRepository;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013 
 */

public abstract class DBaaSRepository implements GenericRepository<DBaaS> {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
}
