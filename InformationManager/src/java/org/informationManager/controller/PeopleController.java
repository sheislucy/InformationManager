/**
 * 
 */
package org.informationManager.controller;

import org.informationManager.dto.HouseMemberRequestDTO;
import org.informationManager.dto.JsonResponseDTO;
import org.informationManager.dto.PeopleRequestDTO;
import org.informationManager.dto.PeopleResponseDTO;
import org.informationManager.service.PeopleService;
import org.informationManager.utils.JsonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sony
 * 
 */
@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController {

	@Autowired
	private PeopleService service;

	@RequestMapping(value = "/hostList.json", method = RequestMethod.POST)
	public @ResponseBody
	PeopleResponseDTO getPeopleList(@RequestBody PeopleRequestDTO requestDTO) {
		PeopleResponseDTO dto = service.getHostList(requestDTO);
		dto.setStatus(JsonStatus.SUCCESS);
		return dto;
	}

	@RequestMapping(value = "/houseMembers/{pid}", method = RequestMethod.GET)
	public ModelAndView getHouseMembers(@PathVariable("pid") String pid) {
		PeopleResponseDTO dto = service
				.getHouseMembers(new HouseMemberRequestDTO());
		dto.setStatus(JsonStatus.SUCCESS);
		ModelAndView mv = new ModelAndView();
		mv.addObject("response", dto);
		mv.setViewName("houseDetail");
		return mv;
	}

	@RequestMapping(value = "/update.json", method = RequestMethod.PUT)
	public @ResponseBody
	JsonResponseDTO updatePeople() {
		return null;
	}

	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponseDTO addPeople() {
		return null;
	}

	@RequestMapping(value = "/delete.json", method = RequestMethod.DELETE)
	public @ResponseBody
	JsonResponseDTO deletePeople() {
		return null;
	}
}
