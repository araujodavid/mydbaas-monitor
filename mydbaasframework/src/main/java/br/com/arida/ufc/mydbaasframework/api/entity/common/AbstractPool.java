package main.java.br.com.arida.ufc.mydbaasframework.api.entity.common;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.api.client.MyDBaaSClient;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.AbstractEntity;

/**
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
 */

public abstract class AbstractPool<T extends AbstractEntity> {
	
	private List<T> pool;
	private MyDBaaSClient client;

	public List<T> getPool() {
		return pool;
	}

	public void setPool(List<T> pool) {
		this.pool = pool;
	}
	
	public MyDBaaSClient getClient() {
		return client;
	}

	public void setClient(MyDBaaSClient client) {
		this.client = client;
	}

	public abstract boolean save(T resource);
	
	public abstract boolean update(T resource);
	
	public abstract boolean delete(T resource);
	
}
