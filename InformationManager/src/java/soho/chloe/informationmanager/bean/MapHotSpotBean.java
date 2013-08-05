package soho.chloe.informationmanager.bean;

import java.util.ArrayList;
import java.util.List;

public class MapHotSpotBean extends JsonResultBean {
	private static final long serialVersionUID = 1L;
	private List<PointDomainBean> points = new ArrayList<PointDomainBean>();
	private List<LineDomainBean> lines = new ArrayList<LineDomainBean>();
	private List<PolygonDomainBean> polygons = new ArrayList<PolygonDomainBean>();

	public List<PointDomainBean> getPoints() {
		return points;
	}

	public void setPoints(List<PointDomainBean> points) {
		this.points = points;
	}

	public List<LineDomainBean> getLines() {
		return lines;
	}

	public void setLines(List<LineDomainBean> lines) {
		this.lines = lines;
	}

	public List<PolygonDomainBean> getPolygons() {
		return polygons;
	}

	public void setPolygons(List<PolygonDomainBean> polygons) {
		this.polygons = polygons;
	}

}
