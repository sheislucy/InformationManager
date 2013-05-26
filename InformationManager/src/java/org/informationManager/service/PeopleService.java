/**
 * 
 */
package org.informationManager.service;

import org.informationManager.dto.HouseMemberRequestDTO;
import org.informationManager.dto.PeopleRequestDTO;
import org.informationManager.dto.PeopleResponseDTO;

/**
 * @author sony
 * 
 */
public interface PeopleService {
	public PeopleResponseDTO getHostList(PeopleRequestDTO requestDTO);

	public PeopleResponseDTO getHouseMembers(HouseMemberRequestDTO requestDTO);
}
