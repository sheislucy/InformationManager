package soho.chloe.informationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.JsonResultBean;
import soho.chloe.informationmanager.bean.LineDomainBean;
import soho.chloe.informationmanager.bean.MapHotSpotBean;
import soho.chloe.informationmanager.bean.PointDomainBean;
import soho.chloe.informationmanager.bean.PolygonDomainBean;
import soho.chloe.informationmanager.service.MapService;
import soho.chloe.informationmanager.utils.JsonStatus;

@Controller
@RequestMapping("/map")
public class MapController extends BaseController {

	@Autowired
	private MapService mapService;

	@RequestMapping(value = "/inc/{mapId}", method = RequestMethod.GET)
	public ModelAndView initMapIncPage(@PathVariable("mapId") int mapId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mapid", mapId);
		mv.addObject("map", mapService.getMapById(mapId));
		mv.setViewName("inc/map.inc");
		return mv;
	}

	@RequestMapping(value = "/edit/{mapId}", method = RequestMethod.GET)
	public ModelAndView goEditMapPage(@PathVariable("mapId") int mapId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mapid", mapId);
		mv.addObject("map", mapService.getMapById(mapId));
		mv.setViewName("editMap");
		return mv;
	}

	@RequestMapping(value = "/hotspots/{mapId}", method = RequestMethod.GET)
	public @ResponseBody
	MapHotSpotBean getHotSpotsByMapId(@PathVariable("mapId") int mapId) {
		MapHotSpotBean bean = new MapHotSpotBean();
		bean.setLines(mapService.getAllLineInTheMap(mapId));
		bean.setPoints(mapService.getAllPointInTheMap(mapId));
		bean.setPolygons(mapService.getAllPolygonInTheMap(mapId));
		bean.setStatus(JsonStatus.SUCCESS);
		return bean;
	}

	@RequestMapping(value = "/save/point", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean savePoint(@RequestBody PointDomainBean requestBean) {
		JsonResultBean result = new JsonResultBean();
		mapService.savePoint(requestBean);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/delete/point", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean deletePoint(@RequestParam("featureId") int featureId) {
		JsonResultBean result = new JsonResultBean();
		mapService.deletePoint(featureId);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/save/line", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean saveLine(@RequestBody LineDomainBean requestBean) {
		JsonResultBean result = new JsonResultBean();
		mapService.saveLine(requestBean);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/delete/line", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean deleteLine(@RequestParam("featureId") int featureId) {
		JsonResultBean result = new JsonResultBean();
		mapService.deleteLine(featureId);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}
	
	@RequestMapping(value = "/save/polygon", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean savePolygon(@RequestBody PolygonDomainBean requestBean) {
		JsonResultBean result = new JsonResultBean();
		mapService.savePolygon(requestBean);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/delete/polygon", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean deletepolygon(@RequestParam("featureId") int featureId) {
		JsonResultBean result = new JsonResultBean();
		mapService.deletePolygon(featureId);
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

}
