package soho.chloe.informationmanager.service;

import java.util.List;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;

public interface HouseService {
	public List<HouseMiniDomainBean> getUnmarkedMiniHouseList();
	
	public GridJsonResponseBean getHouseList(GridJsonRequestBean requestBean);
}
