package main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.common;

/** 
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since April 17, 2013
 * 
 */
public abstract class AbstractEntity {

	private Integer id;
	private String description;
	private String recordDate;
	private String alias;

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

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}	
	
	@Override
	public abstract String toString();
}
