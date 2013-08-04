package soho.chloe.informationmanager.service;

import java.util.List;

import soho.chloe.informationmanager.bean.HouseMiniDomainBean;

public interface HouseService {
	List<HouseMiniDomainBean> getUnmarkedMiniHouseList();
}
