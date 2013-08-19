package soho.chloe.informationmanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lucy
 * 
 */
@Entity
@Table(name = "type_common")
public class CommonType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;

	@Column(name = "typename")
	private String typeName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
