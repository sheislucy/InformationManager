package soho.chloe.informationmanager.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.HouseDomainBean;
import soho.chloe.informationmanager.bean.HouseMiniDomainBean;
import soho.chloe.informationmanager.bean.HousePictureDomainBean;
import soho.chloe.informationmanager.bean.HousePictureUploadBean;
import soho.chloe.informationmanager.bean.JsonResultBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.service.HouseService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.utils.JsonStatus;
import soho.chloe.informationmanager.web.ChloePropertyPlaceholderConfigurer;

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
		return houseService.getHouseWithoutPicturesList(requestBean);
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
		mv.addObject("house", houseService.getOneHouseDetail(houseId));
		return mv;
	}

	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	public @ResponseBody
	HousePictureUploadBean uploadHousePhotos(
			@RequestParam("Filedata") MultipartFile multipartFile,
			@RequestParam("Filename") String fileName,
			@RequestParam("houseId") Integer houseId)
			throws IllegalStateException, IOException {

		StringBuffer pathDir = new StringBuffer()
				.append(ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.HOUSE_IMAGE_DIR));

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH");
		String newName = new StringBuffer().append(UUID.randomUUID())
				.append(".").append(dateformat.format(new Date())).toString();// UUID.YYYY-MM-DD-HH
		// String originalName = multipartFile.getOriginalFilename();
		String pathAndName = pathDir.append(newName).toString();// F:\\upload\\house\\pictures\\UUID.YYYY-MM-DD-HH
		String[] nameExtPair = fileName.split(".");
		String originalExtension = nameExtPair.length > 1 ? "."
				+ nameExtPair[1] : ".png";
		File file = new File(pathAndName + originalExtension);
		multipartFile.transferTo(file);
		HousePictureDomainBean bean = new HousePictureDomainBean();
		bean.setOriginalFileName(fileName);
		bean.setHouseId(houseId);
		bean.setLocalFileName(newName);
		houseService.saveHousePictureThumbnail(file, newName);
		Integer thumbnailId = houseService.savePictureOfHouse(bean);
		HousePictureUploadBean jsonResult = new HousePictureUploadBean();
		jsonResult.setStatus(JsonStatus.SUCCESS);
		jsonResult.setFileName(newName);
		jsonResult.setPictureId(thumbnailId);
		return jsonResult;
	}

	@RequestMapping(value = "/thumbnail", method = RequestMethod.GET)
	public void download(@RequestParam("file") String fileName,
			HttpServletResponse response) throws Exception {
		String path = new StringBuffer()
				.append(ChloePropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.HOUSE_IMAGE_THUMBNAIL_DIR))
				.append(fileName).append("_thumbnail.png").toString();
		File thumbnail = new File(path);
		response.setContentType("image/png; charset=UTF-8");
		response.setHeader("Content-Length", String.valueOf(thumbnail.length()));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				path));
		BufferedOutputStream bos = new BufferedOutputStream(
				response.getOutputStream());
		byte[] buff = new byte[1024 * 1024];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

	@RequestMapping(value = "/saveTextDetail", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean saveTextDetail(@RequestBody HouseDomainBean house) {
		houseService.saveTextDetail(house);
		JsonResultBean result = new JsonResultBean();
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

}
