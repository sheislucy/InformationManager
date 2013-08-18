/**
 * 
 */
package soho.chloe.informationmanager.service;

import java.util.List;

import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.bean.HouseMemberValidationResultBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;

/**
 * @author sony
 * 
 */
public interface PeopleService {
	public GridJsonResponseBean getPeopleList(GridPeopleRequestBean requestBean);

	public GridJsonResponseBean searchPeopleForHouse(
			GridPeopleRequestBean requestBean);

	public HouseMemberValidationResultBean saveMemberRelation(
			List<PeopleDomainBean> memberList);
	// public PeopleResponseDTO getHouseMembers(HouseMemberRequestDTO
	// requestDTO);
}
