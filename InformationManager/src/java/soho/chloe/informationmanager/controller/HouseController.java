package soho.chloe.informationmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
