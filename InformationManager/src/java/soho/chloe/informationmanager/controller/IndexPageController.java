package soho.chloe.informationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.service.MapService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.web.ChloePropertyPlaceholderConfigurer;

@Controller
public class IndexPageController extends BaseController {

	@Autowired
	private MapService mapService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView goPeople() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("mapList", mapService.getAllMaps());
		mv.addObject(
				"defaultPageSize",
				(String) ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE));
		mv.addObject(
				"optionalPageSize",
				(String) ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.OPTIONAL_PAGE_SIZE));
		mv.setViewName("index");
		return mv;
	}
}
