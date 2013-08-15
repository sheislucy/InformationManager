package soho.chloe.informationmanager.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.bean.JsonResultBean;
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
	GridJsonResponseBean getHousePagingList(
			@RequestBody GridJsonRequestBean requestBean) {
		return houseService.getHouseList(requestBean);
	}

	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public ModelAndView initPeopleIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/house.inc");
		return mv;
	}

	@RequestMapping(value = "/editHouse/{houseId}", method = RequestMethod.GET)
	public ModelAndView editHouse(@PathVariable("houseId") Integer houseId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editHouse");
		// TODO
		return mv;
	}

	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean uploadHousePhotos(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");     
        /**构建图片保存的目录**/    
        String logoPathDir = "/files"+ dateformat.format(new Date());     
        /**得到图片保存目录的真实路径**/    
        String logoRealPathDir = request.getSession().getServletContext().getRealPath(logoPathDir);     
        /**根据真实路径创建目录**/    
        File logoSaveFile = new File(logoRealPathDir);     
        if(!logoSaveFile.exists())     
            logoSaveFile.mkdirs();           
        /**页面控件的文件流**/    
        MultipartFile multipartFile = multipartRequest.getFile("file");      
        /**获取文件的后缀**/    
        String suffix = multipartFile.getOriginalFilename().substring  
                        (multipartFile.getOriginalFilename().lastIndexOf("."));     
//        /**使用UUID生成文件名称**/    
//        String logImageName = UUID.randomUUID().toString()+ suffix;//构建文件名称     
        String logImageName = multipartFile.getOriginalFilename();  
        /**拼成完整的文件保存路径加文件**/    
        String fileName = logoRealPathDir + File.separator   + logImageName;                
        File file = new File(fileName);          
        
        try {     
            multipartFile.transferTo(file);     
        } catch (IllegalStateException e) {     
            e.printStackTrace();     
        } catch (IOException e) {            
            e.printStackTrace();     
        }     
		
		return null;
	}
}
