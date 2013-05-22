/**
 * 
 */
package org.informationManager.service;

import org.informationManager.dto.PeopleRequestDTO;
import org.informationManager.dto.PeopleResponseDTO;

/**
 * @author sony
 *
 */
public interface PeopleService {
	public PeopleResponseDTO getPeopleList(PeopleRequestDTO requestDTO);
}
