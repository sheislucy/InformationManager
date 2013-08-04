package soho.chloe.informationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.JsonPointRequestBean;
import soho.chloe.informationmanager.bean.JsonResultBean;
import soho.chloe.informationmanager.service.MapService;
import soho.chloe.informationmanager.utils.JsonStatus;

@Controller
@RequestMapping("/map")
public class MapController extends BaseController {

	@Autowired
	private MapService mapService;

	@RequestMapping(value = "/inc/{mapid}", method = RequestMethod.GET)
	public ModelAndView initMapIncPage(@PathVariable("mapid") String mapid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mapid", mapid);
		mv.setViewName("inc/map.inc");
		return mv;
	}

	@RequestMapping(value = "/edit/{mapid}", method = RequestMethod.GET)
	public ModelAndView goEditMapPage(@PathVariable("mapid") int mapid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mapid", mapid);
		mv.addObject("map", mapService.getMapById(mapid));
		mv.setViewName("editMap");
		return mv;
	}

	@RequestMapping(value = "/save/point", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean savePoint(@RequestBody JsonPointRequestBean requestBean) {
		JsonResultBean result = new JsonResultBean();
		mapService.savePoint(requestBean);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

}
