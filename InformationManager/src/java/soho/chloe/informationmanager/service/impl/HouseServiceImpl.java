package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.entity.HouseEntity;
import soho.chloe.informationmanager.repositories.HouseDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.HouseService;

@Service
public class HouseServiceImpl extends BaseService implements HouseService {

	@Autowired
	private HouseDao houseDao;

	@Override
	public List<HouseMiniDomainBean> getUnmarkedMiniHouseList() {
		return buildHouseMiniDomainBean(houseDao.getUnmarkedHouse());
	}

	private List<HouseMiniDomainBean> buildHouseMiniDomainBean(
			List<HouseEntity> entityList) {
		List<HouseMiniDomainBean> miniBeanList = new ArrayList<HouseMiniDomainBean>();
		for (HouseEntity entity : entityList) {
			HouseMiniDomainBean bean = new HouseMiniDomainBean();
			bean.setId(entity.getId());
			bean.setName(entity.getHost() != null ? entity.getHost().getName()
					+ " - " + entity.getId() : null);
			miniBeanList.add(bean);
		}

		return miniBeanList;
	}
}
