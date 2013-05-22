/**
 * 
 */
package org.informationManager.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sony
 * 
 */
public class PeopleResponseDTO extends JsonResponseDTO {

	private static final long serialVersionUID = -8500231562243708992L;

	private List<PeopleDomain> peopleList = new ArrayList<PeopleDomain>();
	
	public List<PeopleDomain> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<PeopleDomain> peopleList) {
		this.peopleList = peopleList;
	}

}
