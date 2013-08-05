package soho.chloe.informationmanager.service;

import java.util.List;

import soho.chloe.informationmanager.bean.LineDomainBean;
import soho.chloe.informationmanager.bean.MapDomainBean;
import soho.chloe.informationmanager.bean.PointDomainBean;
import soho.chloe.informationmanager.bean.PolygonDomainBean;

public interface MapService {
	public List<MapDomainBean> getAllMaps();

	public MapDomainBean getMapById(int id);

	public void savePoint(PointDomainBean pointBean);

	public List<PointDomainBean> getAllPointInTheMap(int mapId);
	
	public List<LineDomainBean> getAllLineInTheMap(int mapId);
	
	public List<PolygonDomainBean> getAllPolygonInTheMap(int mapId);
}
