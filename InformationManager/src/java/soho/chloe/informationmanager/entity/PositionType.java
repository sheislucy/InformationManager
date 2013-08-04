package soho.chloe.informationmanager.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lucy
 *
 */
@Entity
@Table(name = "type_position")
public class PositionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
