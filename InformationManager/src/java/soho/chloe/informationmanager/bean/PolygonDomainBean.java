package soho.chloe.informationmanager.bean;

import java.io.Serializable;

public class PolygonDomainBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private int mapId;
	private String description;
	private String coordination;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoordination() {
		return coordination;
	}

	public void setCoordination(String coordination) {
		this.coordination = coordination;
	}

}
