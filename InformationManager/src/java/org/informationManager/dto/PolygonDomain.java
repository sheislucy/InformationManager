package org.informationManager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class PolygonDomain implements Serializable {
	private String featureId;

	private List<ZoneDomain> polygon = new ArrayList<ZoneDomain>();

	public List<ZoneDomain> getPolygon() {
		return polygon;
	}

	public void setPolygon(List<ZoneDomain> polygon) {
		this.polygon = polygon;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

}
