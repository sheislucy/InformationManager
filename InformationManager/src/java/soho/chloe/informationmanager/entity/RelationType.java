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
@Table(name = "type_relation")
public class RelationType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;

	@Column(name = "relationname")
	private String relationName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

}
