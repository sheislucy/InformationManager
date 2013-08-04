/**
 * 
 */
package soho.chloe.informationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.service.PeopleService;
import soho.chloe.informationmanager.utils.JsonStatus;

/**
 * @author sony
 * 
 */
@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController {

	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public ModelAndView initPeopleIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/people.inc");
		return mv;
	}
	
	@Autowired
	private PeopleService service;

	@RequestMapping(value = "/people.json", method = RequestMethod.POST)
	public @ResponseBody
	GridJsonResponseBean getPeopleList(@RequestBody GridPeopleRequestBean requestDTO) {
		GridJsonResponseBean dto = service.getPeopleList(requestDTO);
		return dto;
	}

	@RequestMapping(value = "/houseMembers/{pid}", method = RequestMethod.GET)
	public ModelAndView getHouseMembers(@PathVariable("pid") String pid) {
		//TODO fake pid, real one hasn't been passed in
//		PeopleResponseDTO dto = service
//				.getHouseMembers(new HouseMemberRequestDTO());
//		dto.setStatus(JsonStatus.SUCCESS);
		ModelAndView mv = new ModelAndView();
//		mv.addObject("response", dto);
		mv.setViewName("houseDetail");
		return mv;
	}

//	@RequestMapping(value = "/update.json", method = RequestMethod.PUT)
//	public @ResponseBody
//	JsonResponseDTO updatePeople() {
//		return null;
//	}
//
//	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
//	public @ResponseBody
//	JsonResponseDTO addPeople() {
//		return null;
//	}
//
//	@RequestMapping(value = "/delete.json", method = RequestMethod.DELETE)
//	public @ResponseBody
//	JsonResponseDTO deletePeople() {
//		return null;
//	}
}
