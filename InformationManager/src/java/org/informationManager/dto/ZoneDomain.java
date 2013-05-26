package org.informationManager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ZoneDomain implements Serializable {
	private List<PointDomain> zones = new ArrayList<PointDomain>();

	public List<PointDomain> getZones() {
		return zones;
	}

	public void setZones(List<PointDomain> zones) {
		this.zones = zones;
	}
}
