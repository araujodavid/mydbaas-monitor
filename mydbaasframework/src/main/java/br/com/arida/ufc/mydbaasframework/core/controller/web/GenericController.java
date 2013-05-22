package main.java.br.com.arida.ufc.mydbaasframework.core.controller.web;

import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.common.AbstractEntity;

/**
 * This interface is for a base Controller.
 * This interface uses generic parameters to create a Controller for a specific entity.
 * Basic methods for manipulating an object.
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013
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
