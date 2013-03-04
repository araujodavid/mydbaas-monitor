package main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.common;

/** 
 * 
 * @author David Araújo
 * @since March 4, 2013
 * 
 */

public abstract class AbstractEntity {

	private Integer id;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
