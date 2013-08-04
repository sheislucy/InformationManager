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
@Table(name = "type_political")
public class PoliticalType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String political;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

}
