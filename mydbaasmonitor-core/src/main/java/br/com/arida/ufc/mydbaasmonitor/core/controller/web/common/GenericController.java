package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.web.common;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common.AbstractEntity;

/**
 * This interface is for a base Controller.
 * This interface uses generic parameters to create a Controller for a specific entity.
 * Basic methods for manipulating an object.
 * 
 * @author David Araújo
 * @version 1.0
 * @since March 18, 2013
 * 
 */

public interface GenericController<T extends AbstractEntity> {

	public void redirect();
	
	public List<T> list();
	
	public void form();
	
	public void add(T entity);
	
	public T edit(T entity);
	
	public void update(T entity);
	
	public T view(T entity);
	
	public void delete(T entity);
}
