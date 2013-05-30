package org.informationManager.controller;

import org.informationManager.utils.InformationManagerConstants;
import org.informationManager.web.MyPropertyPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DynamicPageController extends BaseController {

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
		mv.setViewName("index");
		return mv;
	}

//	@RequestMapping(value = "/map", method = RequestMethod.GET)
//	public ModelAndView initMapPage() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("map");
//		return mv;
//	}
	
	@RequestMapping(value = "/people/inc", method = RequestMethod.GET)
	public ModelAndView initPeopleIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/people.inc");
		return mv;
	}
	
	@RequestMapping(value = "/map/inc", method = RequestMethod.GET)
	public ModelAndView initMapIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/map.inc");
		return mv;
	}

	@RequestMapping(value = "/chart", method = RequestMethod.GET)
	public ModelAndView initDefaultStatisticsPage() {
		return initStatisticsPage("1");
	}

	@RequestMapping(value = "/chart/{chartId}", method = RequestMethod.GET)
	public ModelAndView initStatisticsPage(
			@PathVariable("chartId") String chartId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("chartId", chartId);
		mv.setViewName("inc/chart.inc");
		return mv;
	}
}
