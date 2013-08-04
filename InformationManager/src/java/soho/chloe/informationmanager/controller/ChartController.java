package soho.chloe.informationmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChartController extends BaseController {
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
