package main.java.br.com.arida.ufc.mydbaasframework.core.repository.connection;

import java.sql.Connection;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013 
 */

public abstract class AbstractDatabaseConnection {

	public abstract Connection getConnection();
}
