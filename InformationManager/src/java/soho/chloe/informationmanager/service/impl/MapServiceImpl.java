package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soho.chloe.informationmanager.bean.JsonPointRequestBean;
import soho.chloe.informationmanager.bean.MapDomainBean;
import soho.chloe.informationmanager.entity.GeoPointEntity;
import soho.chloe.informationmanager.entity.MapEntity;
import soho.chloe.informationmanager.repositories.GeoPointDao;
import soho.chloe.informationmanager.repositories.MapDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.MapService;

@Service
public class MapServiceImpl extends BaseService implements MapService {

	@Autowired
	private MapDao mapDao;

	@Autowired
	private GeoPointDao pointDao;

	@Override
	public List<MapDomainBean> getAllMaps() {
		return buildMapDomainBeanList(mapDao.findAll());
	}

	@Override
	public MapDomainBean getMapById(int id) {
		return buildMapDomainBean(mapDao.findById(id));
	}

	@Override
	public void savePoint(JsonPointRequestBean pointBean) {
		GeoPointEntity pointEntity = new GeoPointEntity();
		pointEntity.setMapId(pointBean.getMapId());
		pointEntity.setCoordination(pointBean.getCoordination());
		pointEntity.setDescription(pointBean.getDescription());
		pointEntity.setHouseId(pointBean.getHouseId());
		pointDao.saveAndFlush(pointEntity);
	}

	private MapDomainBean buildMapDomainBean(MapEntity entity) {
		MapDomainBean bean = new MapDomainBean();

		bean.setDescription(entity.getDescription());
		bean.setHeight(entity.getHeight());
		bean.setId(entity.getId());
		bean.setMapName(entity.getMapName());
		bean.setType(entity.getType());
		bean.setUrl(entity.getUrl());
		bean.setWidth(entity.getWidth());
		return bean;
	}

	private List<MapDomainBean> buildMapDomainBeanList(
			List<MapEntity> mapEntityList) {
		List<MapDomainBean> mapBeanList = new ArrayList<MapDomainBean>();
		for (MapEntity entity : mapEntityList) {
			mapBeanList.add(buildMapDomainBean(entity));
		}
		return mapBeanList;
	}

}
