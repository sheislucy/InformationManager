package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseDomainBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.entity.HouseEntity;
import soho.chloe.informationmanager.repositories.HouseDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.HouseService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.web.MyPropertyPlaceholderConfigurer;

@Service
public class HouseServiceImpl extends BaseService implements HouseService {

	@Autowired
	private HouseDao houseDao;

	@Override
	public List<HouseMiniDomainBean> getUnmarkedMiniHouseList() {
		return buildHouseMiniDomainBean(houseDao.getUnmarkedHouse());
	}

	@Override
	public GridJsonResponseBean getHouseList(GridJsonRequestBean requestBean) {
		if (requestBean.getRows() <= 0) {
			requestBean
					.setRows(Integer.parseInt((String) MyPropertyPlaceholderConfigurer
							.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE)));
		}
		int totalCount = 0;
		List<HouseEntity> houseList = new ArrayList<HouseEntity>();

		totalCount = (int) houseDao.count();
		houseList.addAll(houseDao.findAll(buildPageRequest(requestBean))
				.getContent());

		int rowsInPage = requestBean.getRows();
		GridJsonResponseBean response = new GridJsonResponseBean();
		response.setTotal(totalCount / rowsInPage + 1);
		response.setRecords(totalCount);
		buildResponse(response, houseList);
		return response;
	}

	private void buildResponse(GridJsonResponseBean response,
			List<HouseEntity> houseList) {
		response.getRows().clear();
		for (HouseEntity entity : houseList) {
			HouseDomainBean bean = new HouseDomainBean();
			bean.setAddress(entity.getAddress());
			bean.setApprovalsNo(entity.getApprovalsNo());
			bean.setBuildingAge(entity.getBuildingAge());
			bean.setBuildingArea(entity.getBuildingArea());
			bean.setBuildStruct(entity.getBuildStruct());
			bean.setFaceTo(entity.getFaceTo());
			bean.setFloorCount(entity.getFloorCount());
			bean.setHasWall(entity.getHasWall());
			bean.setId(entity.getId());
			bean.setIsDangerous(entity.getIsDangerous());
			bean.setIsLegal(entity.getIsLegal());
			bean.setLandArea(entity.getLandArea());
			bean.setLandClass(entity.getLandClass());
			bean.setLandNo(entity.getLandNo());
			bean.setLastUpdateTime(entity.getLastUpdateTime());
			bean.setName(entity.getHost() != null ? entity.getHost().getName()
					: null);
			bean.setParcelNo(entity.getParcelNo());
			bean.setPid(entity.getPid());
			bean.setProperty(entity.getProperty());
			bean.setPropertyNo(entity.getPropertyNo());
			response.getRows().add(bean);
		}
	}

	private PageRequest buildPageRequest(GridJsonRequestBean requestDTO) {
		Sort sort = null;
		if (StringUtils.isEmpty(requestDTO.getSidx())
				&& StringUtils.isEmpty(requestDTO.getSord())) {
			sort = new Sort(Direction.DESC, "id");
		} else if (StringUtils.isEmpty(requestDTO.getSidx())) {
			if (requestDTO.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, "id");
			} else {
				sort = new Sort(Direction.ASC, "id");
			}
		} else if (StringUtils.isEmpty(requestDTO.getSord())) {
			sort = new Sort(Direction.DESC, requestDTO.getSidx());
		} else {
			if (requestDTO.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, requestDTO.getSidx());
			} else {
				sort = new Sort(Direction.ASC, requestDTO.getSidx());
			}
		}

		return new PageRequest(requestDTO.getPage() - 1, requestDTO.getRows(),
				sort);
	}

	private List<HouseMiniDomainBean> buildHouseMiniDomainBean(
			List<HouseEntity> entityList) {
		List<HouseMiniDomainBean> miniBeanList = new ArrayList<HouseMiniDomainBean>();
		for (HouseEntity entity : entityList) {
			HouseMiniDomainBean bean = new HouseMiniDomainBean();
			bean.setId(entity.getId());
			bean.setName(entity.getHost() != null ? entity.getHost().getName()
					+ " - 户主id: " + entity.getHost().getPid() : null);
			miniBeanList.add(bean);
		}

		return miniBeanList;
	}
}
