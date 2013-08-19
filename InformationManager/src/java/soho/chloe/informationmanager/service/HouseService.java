package soho.chloe.informationmanager.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseDomainBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.bean.HousePictureDomainBean;

public interface HouseService {
	public List<HouseMiniDomainBean> getMiniHostBeanListUnmarkedOnMap();

	public GridJsonResponseBean getHouseWithoutPicturesList(GridJsonRequestBean requestBean);

	public Integer savePictureOfHouse(HousePictureDomainBean picture);

	public void saveHousePictureThumbnail(File originalFile, String fileName) throws IOException;

	public HouseDomainBean getOneHouseDetail(Integer houseId);

	public void saveTextDetail(HouseDomainBean house);

	public void deleteHouse(int houseId);

	public int createNewHouse(int hostId);

	public void deletePicture(int pictureId);
}
