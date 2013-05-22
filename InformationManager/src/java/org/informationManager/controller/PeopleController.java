/**
 * 
 */
package org.informationManager.controller;

import org.informationManager.dto.JsonResponseDTO;
import org.informationManager.dto.PeopleRequestDTO;
import org.informationManager.dto.PeopleResponseDTO;
import org.informationManager.service.PeopleService;
import org.informationManager.utils.InformationManagerConstants;
import org.informationManager.utils.JsonStatus;
import org.informationManager.utils.MyPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView goPeople() {
		ModelAndView mv = new ModelAndView();
		mv.addObject(
				"defaultPageSize",
				(String) MyPropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE));
		mv.addObject(
				"optionalPageSize",
				(String) MyPropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.OPTIONAL_PAGE_SIZE));
		mv.setViewName("people");
		return mv;
	}

	@RequestMapping(value = "/getList.json", method = RequestMethod.POST)
	public @ResponseBody
	PeopleResponseDTO getPeopleList(@RequestBody PeopleRequestDTO requestDTO) {
		PeopleResponseDTO dto = service.getPeopleList(requestDTO);
		dto.setStatus(JsonStatus.SUCCESS);
		return dto;
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
