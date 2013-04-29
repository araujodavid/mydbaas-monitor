package main.java.br.com.arida.ufc.mydbaasmonitor.api.entity.common;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common.AbstractEntity;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since April 28, 2013
 */

public abstract class AbstractPool<T extends AbstractEntity> {
	
	private List<T> pool;

	public List<T> getPool() {
		return pool;
	}

	public void setPool(List<T> pool) {
		this.pool = pool;
	}
	
	public abstract boolean save(T resource);
	
	public abstract boolean update(T resource);
	
}
