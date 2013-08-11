package soho.chloe.informationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {

	@Autowired
	private HouseService houseService;

	@RequestMapping(value = "/miniList/unmarked")
	public @ResponseBody
	List<HouseMiniDomainBean> getMiniHouseBeanList() {
		return houseService.getUnmarkedMiniHouseList();
	}

	@RequestMapping(value = "/house.json")
	public @ResponseBody
	GridJsonResponseBean getHousePagingList(@RequestBody GridJsonRequestBean requestBean) {
		return houseService.getHouseList(requestBean);
	}
	
	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public ModelAndView initPeopleIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/house.inc");
		return mv;
	}
}
