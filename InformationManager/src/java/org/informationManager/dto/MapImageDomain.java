package org.informationManager.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MapImageDomain implements Serializable {
	private String mapId;

	private String mapName;

	private String mapImageUrl;

	private double width;

	private double height;

	public String getMapImageUrl() {
		return mapImageUrl;
	}

	public void setMapImageUrl(String mapImageUrl) {
		this.mapImageUrl = mapImageUrl;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

}
