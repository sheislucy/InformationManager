/**
 * 
 */
package soho.chloe.informationmanager.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.bean.ValidationResultBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.bean.PeopleMiniDomainBean;

/**
 * @author sony
 * 
 */
public interface PeopleService {
	public GridJsonResponseBean getPeopleList(GridPeopleRequestBean requestBean);

	public GridJsonResponseBean searchPeopleForHouse(GridPeopleRequestBean requestBean);

	public ValidationResultBean saveMemberRelation(List<PeopleDomainBean> memberList);

	public List<PeopleMiniDomainBean> getMiniPeopleBeanListWithNoHouse();

	public PeopleDomainBean getPeople(int pid);

	public void updatePeoplePicture(int pid, String fileName);

	public void savePeoplePictureThumbnail(File originalFile, String fileName) throws IOException;

	public void deletePicture(int pid);

	public void saveTextDetail(PeopleDomainBean people);

	public void saveSelectDetail(PeopleDomainBean people);

	public int createPeople(String name);

	public ValidationResultBean deletePeople(int pid);
}
