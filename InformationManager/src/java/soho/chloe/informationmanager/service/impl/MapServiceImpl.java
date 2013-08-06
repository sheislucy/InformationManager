package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soho.chloe.informationmanager.bean.LineDomainBean;
import soho.chloe.informationmanager.bean.MapDomainBean;
import soho.chloe.informationmanager.bean.PointDomainBean;
import soho.chloe.informationmanager.bean.PolygonDomainBean;
import soho.chloe.informationmanager.entity.GeoLineEntity;
import soho.chloe.informationmanager.entity.GeoPointEntity;
import soho.chloe.informationmanager.entity.GeoPolygonEntity;
import soho.chloe.informationmanager.entity.MapEntity;
import soho.chloe.informationmanager.repositories.GeoLineDao;
import soho.chloe.informationmanager.repositories.GeoPointDao;
import soho.chloe.informationmanager.repositories.GeoPolygonDao;
import soho.chloe.informationmanager.repositories.MapDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.MapService;

@Service
public class MapServiceImpl extends BaseService implements MapService {

	@Autowired
	private MapDao mapDao;

	@Autowired
	private GeoPointDao pointDao;

	@Autowired
	private GeoLineDao lineDao;

	@Autowired
	private GeoPolygonDao polygonDao;

	@Override
	public List<MapDomainBean> getAllMaps() {
		return buildMapDomainBeanList(mapDao.findAll());
	}

	@Override
	public MapDomainBean getMapById(int id) {
		return buildMapDomainBean(mapDao.findById(id));
	}

	@Override
	public void savePoint(PointDomainBean pointBean) {
		if (pointBean.getId() != null) {
			// update
			GeoPointEntity pointEntity = pointDao.findOne(pointBean.getId());
			if (pointEntity == null) {
				//if can't find the one to be updated (multiple threads will cause this problem), force to insert one
				pointEntity = new GeoPointEntity();
				pointEntity.setId(pointBean.getId());
			}
			boolean isChanged = false;
			if (pointBean.getCoordination() != null) {
				pointEntity.setCoordination(pointBean.getCoordination());
				isChanged = true;
			}
			if (pointBean.getDescription() != null) {
				pointEntity.setDescription(pointBean.getDescription());
				isChanged = true;
			}
			if (isChanged) {
				pointDao.save(pointEntity);
			}
		} else {
			// insert
			GeoPointEntity pointEntity = new GeoPointEntity();
			pointEntity.setMapId(pointBean.getMapId());
			pointEntity.setCoordination(pointBean.getCoordination());
			pointEntity.setDescription(pointBean.getDescription());
			pointEntity.setHouseId(pointBean.getHouseId());
			pointDao.save(pointEntity);
		}
	}

	@Override
	public List<PointDomainBean> getAllPointInTheMap(int mapId) {
		return buildPointDomainBeanList(pointDao.findByMapId(mapId));
	}

	@Override
	public List<LineDomainBean> getAllLineInTheMap(int mapId) {
		return buildLineDomainBeanList(lineDao.findByMapId(mapId));
	}

	@Override
	public List<PolygonDomainBean> getAllPolygonInTheMap(int mapId) {
		return buildPolygonDomainBeanList(polygonDao.findByMapId(mapId));
	}

	@Override
	public void deletePoint(int featureId) {
		pointDao.delete(featureId);
	}

	@Override
	public void saveLine(LineDomainBean lineBean) {
		if (lineBean.getId() != null) {
			// update
			GeoLineEntity entity = lineDao.findOne(lineBean.getId());
			if (entity == null) {
				if (entity == null) {
					//if can't find the one to be updated (multiple threads will cause this problem), force to insert one
					entity = new GeoLineEntity();
					entity.setId(entity.getId());
				}
			}
			boolean isChanged = false;
			if (lineBean.getCoordination() != null) {
				entity.setCoordination(lineBean.getCoordination());
				isChanged = true;
			}
			if (lineBean.getDescription() != null) {
				entity.setDescription(lineBean.getDescription());
				isChanged = true;
			}
			if (isChanged) {
				lineDao.save(entity);
			}
		} else {
			// insert
			GeoLineEntity entity = new GeoLineEntity();
			entity.setCoordination(lineBean.getCoordination());
			entity.setDescription(lineBean.getDescription());
			entity.setMapId(lineBean.getMapId());
			lineDao.save(entity);
		}
	}

	@Override
	public void deleteLine(int featureId) {
		lineDao.delete(featureId);
	}

	@Override
	public void savePolygon(PolygonDomainBean polygonBean) {
		if (polygonBean.getId() != null) {
			// update
			GeoPolygonEntity entity = polygonDao.findOne(polygonBean.getId());
			if (entity == null) {
				if (entity == null) {
					//if can't find the one to be updated (multiple threads will cause this problem), force to insert one
					entity = new GeoPolygonEntity();
					entity.setId(entity.getId());
				}
			}
			boolean isChanged = false;
			if (polygonBean.getCoordination() != null) {
				entity.setCoordination(polygonBean.getCoordination());
				isChanged = true;
			}
			if (polygonBean.getDescription() != null) {
				entity.setDescription(polygonBean.getDescription());
				isChanged = true;
			}
			if (isChanged) {
				polygonDao.save(entity);
			}
		} else {
			// insert
			GeoPolygonEntity entity = new GeoPolygonEntity();
			entity.setCoordination(polygonBean.getCoordination());
			entity.setDescription(polygonBean.getDescription());
			entity.setMapId(polygonBean.getMapId());
			polygonDao.save(entity);
		}
	}

	@Override
	public void deletePolygon(int featureId) {
		polygonDao.delete(featureId);
	}

	private List<PolygonDomainBean> buildPolygonDomainBeanList(
			List<GeoPolygonEntity> entityList) {
		List<PolygonDomainBean> beanList = new ArrayList<PolygonDomainBean>();
		for (GeoPolygonEntity entity : entityList) {
			beanList.add(buildPolygonDomainBean(entity));
		}
		return beanList;
	}

	private PolygonDomainBean buildPolygonDomainBean(GeoPolygonEntity entity) {
		PolygonDomainBean bean = new PolygonDomainBean();
		bean.setCoordination(entity.getCoordination());
		bean.setDescription(entity.getDescription());
		bean.setMapId(entity.getMapId());
		bean.setId(entity.getId());
		return bean;
	}

	private List<LineDomainBean> buildLineDomainBeanList(
			List<GeoLineEntity> entityList) {
		List<LineDomainBean> beanList = new ArrayList<LineDomainBean>();
		for (GeoLineEntity entity : entityList) {
			beanList.add(buildLineDomainBean(entity));
		}
		return beanList;
	}

	private LineDomainBean buildLineDomainBean(GeoLineEntity entity) {
		LineDomainBean bean = new LineDomainBean();
		bean.setCoordination(entity.getCoordination());
		bean.setDescription(entity.getDescription());
		bean.setMapId(entity.getMapId());
		bean.setId(entity.getId());
		return bean;
	}

	private List<PointDomainBean> buildPointDomainBeanList(
			List<GeoPointEntity> entityList) {
		List<PointDomainBean> beanList = new ArrayList<PointDomainBean>();
		for (GeoPointEntity entity : entityList) {
			beanList.add(buildPointDomainBean(entity));
		}
		return beanList;
	}

	private PointDomainBean buildPointDomainBean(GeoPointEntity entity) {
		PointDomainBean bean = new PointDomainBean();
		bean.setCoordination(entity.getCoordination());
		bean.setDescription(entity.getDescription());
		bean.setHouseId(entity.getHouseId());
		bean.setMapId(entity.getMapId());
		bean.setId(entity.getId());
		bean.setHostName(entity.getHouse().getHost().getName());
		bean.setHostId(entity.getHouse().getHost().getPid());
		return bean;
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
