package soho.chloe.informationmanager.service;

import java.util.List;

import soho.chloe.informationmanager.bean.JsonPointRequestBean;
import soho.chloe.informationmanager.bean.MapDomainBean;

public interface MapService {
	public List<MapDomainBean> getAllMaps();
	
	public MapDomainBean getMapById(int id);
	
	public void savePoint(JsonPointRequestBean pointBean);
}
