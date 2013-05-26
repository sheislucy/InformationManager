package org.informationManager.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PointDomain implements Serializable {
	private String featureId;

	private double x = 0.0d;

	private double y = 0.0d;

	public PointDomain() {
	}

	public PointDomain(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public PointDomain(double x, double y, String featureId) {
		this.x = x;
		this.y = y;
		this.featureId = featureId;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

}
