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
@Table(name = "type_social")
public class SocialType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String social;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

}
