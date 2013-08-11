/**
 * 
 */
package soho.chloe.informationmanager.service;

import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;

/**
 * @author sony
 * 
 */
public interface PeopleService {
	public GridJsonResponseBean getPeopleList(GridPeopleRequestBean requestBean);

//	public PeopleResponseDTO getHouseMembers(HouseMemberRequestDTO requestDTO);
}
