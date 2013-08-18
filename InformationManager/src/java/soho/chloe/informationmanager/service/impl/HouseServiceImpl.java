package soho.chloe.informationmanager.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseDomainBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.bean.HousePictureDomainBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.entity.HouseEntity;
import soho.chloe.informationmanager.entity.HousePictureEntity;
import soho.chloe.informationmanager.entity.PeopleEntity;
import soho.chloe.informationmanager.repositories.HouseDao;
import soho.chloe.informationmanager.repositories.HousePictureDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.HouseService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.web.ChloePropertyPlaceholderConfigurer;

@Service
public class HouseServiceImpl extends BaseService implements HouseService {

	@Autowired
	private HouseDao houseDao;

	@Autowired
	private HousePictureDao housePictureDao;

	@Override
	public List<HouseMiniDomainBean> getUnmarkedMiniHouseList() {
		return buildHouseMiniDomainBean(houseDao.getUnmarkedHouse());
	}

	@Override
	public GridJsonResponseBean getHouseWithoutPicturesList(
			GridJsonRequestBean requestBean) {
		if (requestBean.getRows() <= 0) {
			requestBean
					.setRows(Integer
							.parseInt((String) ChloePropertyPlaceholderConfigurer
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Integer savePictureOfHouse(HousePictureDomainBean picture) {
		HousePictureEntity entity = new HousePictureEntity();
		entity.setOriginalFileName(picture.getOriginalFileName());
		entity.setHouseId(picture.getHouseId());
		entity.setLocalFileName(picture.getLocalFileName());
		entity = housePictureDao.save(entity);
		return entity.getId();
	}

	@Override
	public void saveHousePictureThumbnail(File originalFile, String fileName)
			throws IOException {
		Thumbnails
				.of(originalFile)
				.size(320, 240)
				.outputFormat("png")
				.toFile(ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.HOUSE_IMAGE_THUMBNAIL_DIR)
						+ fileName + "_thumbnail");
	}

	@Override
	public HouseDomainBean getOneHouseDetail(Integer houseId) {
		HouseEntity houseEntity = houseDao.findOne(houseId);
		List<HousePictureEntity> housePictureEntityList = housePictureDao
				.findByHouseId(houseId);
		HouseDomainBean houseBean = buildHouseDomainBean(houseEntity);
		for (HousePictureEntity entity : housePictureEntityList) {
			houseBean.getPictures().add(buildHousePictureDomainBean(entity));
		}
		return houseBean;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveTextDetail(HouseDomainBean house) {
		HouseEntity entity = houseDao.findOne(house.getId());
		entity.setAddress(house.getAddress());
		entity.setUseType(house.getUseType());
		entity.setBuildStruct(house.getBuildStruct());
		entity.setFloorCount(house.getFloorCount());
		entity.setLandClass(house.getLandClass());
		entity.setLandArea(house.getLandArea());
		entity.setBuildingArea(house.getBuildingArea());
		entity.setBuildingAge(house.getBuildingAge());
		entity.setProperty(house.getProperty());
		entity.setPropertyNo(house.getPropertyNo());
		entity.setLandNo(house.getLandNo());
		entity.setApprovalsNo(house.getApprovalsNo());
		entity.setParcelNo(house.getParcelNo());
		entity.setFaceTo(house.getFaceTo());
		entity.setHasWall(house.getHasWall());
		entity.setIsDangerous(house.getIsDangerous());
		entity.setIsLegal(house.getIsLegal());
		houseDao.save(entity);
	}

	private HousePictureDomainBean buildHousePictureDomainBean(
			HousePictureEntity pictureEntity) {
		HousePictureDomainBean pictureBean = new HousePictureDomainBean();
		pictureBean.setHouseId(pictureEntity.getHouseId());
		pictureBean.setDescription(pictureEntity.getDescription());
		pictureBean.setId(pictureEntity.getId());
		pictureBean.setOriginalFileName(pictureEntity.getOriginalFileName());
		pictureBean.setLocalFileName(pictureEntity.getLocalFileName());
		return pictureBean;
	}

	private void buildResponse(GridJsonResponseBean response,
			List<HouseEntity> houseList) {
		response.getRows().clear();
		for (HouseEntity entity : houseList) {
			response.getRows().add(buildHouseDomainBean(entity));
		}
	}

	private HouseDomainBean buildHouseDomainBean(HouseEntity entity) {
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
		bean.setUseType(entity.getUseType());
		if (!entity.getHouseMembers().isEmpty()) {
			for (PeopleEntity people : entity.getHouseMembers()) {
				if (people.getRelationId() != null
						&& people.getRelationId() == 1) {
					bean.setName(people.getName());
					bean.setPid(people.getPid());
				}
				bean.getMembers().add(buildPeopleDomainBean(people));
			}
		}
		bean.setProperty(entity.getProperty());
		bean.setPropertyNo(entity.getPropertyNo());
		bean.setParcelNo(entity.getParcelNo());
		bean.setFaceTo(entity.getFaceTo());
		return bean;
	}

	private PeopleDomainBean buildPeopleDomainBean(PeopleEntity entity) {
		PeopleDomainBean bean = new PeopleDomainBean();
		bean.setPid(entity.getPid());
		bean.setName(entity.getName());
		bean.setCardId(entity.getCardId());
		bean.setBirthday(entity.getBirthday());
		bean.setGender((entity.getGender() != null && entity.getGender() == 1) ? "男"
				: "女");
		bean.setRelation(String.valueOf(entity.getRelationId()));
		return bean;
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
			if (!entity.getHouseMembers().isEmpty()) {
				for (PeopleEntity people : entity.getHouseMembers()) {
					if (people.getRelationId() == 1) {
						bean.setName(people.getName() + " - 户主id: "
								+ people.getPid());
						break;
					}
				}
			}
			miniBeanList.add(bean);
		}

		return miniBeanList;
	}

}
