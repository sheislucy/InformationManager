/**
 * 
 */
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

import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.bean.ValidationResultBean;
import soho.chloe.informationmanager.bean.JsonResultBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.bean.PeopleMiniDomainBean;
import soho.chloe.informationmanager.bean.PictureUploadBean;
import soho.chloe.informationmanager.service.PeopleService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.utils.JsonStatus;
import soho.chloe.informationmanager.web.InforPropertyPlaceholderConfigurer;

/**
 * @author sony
 * 
 */
@Controller
@RequestMapping("/people")
public class PeopleController extends BaseController {
	@Autowired
	private PeopleService service;

	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public ModelAndView initPeopleIncPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inc/people.inc");
		return mv;
	}

	@RequestMapping(value = "/people.json", method = RequestMethod.POST)
	public @ResponseBody
	GridJsonResponseBean getPeoplePagingList(
			@RequestBody GridPeopleRequestBean requestBean) {
		GridJsonResponseBean response = service.getPeopleList(requestBean);
		return response;
	}

	@RequestMapping(value = "/houseMembers/{pid}", method = RequestMethod.GET)
	public ModelAndView getHouseMembers(@PathVariable("pid") String pid) {
		// TODO fake pid, real one hasn't been passed in
		// PeopleResponseDTO dto = service
		// .getHouseMembers(new HouseMemberRequestDTO());
		// dto.setStatus(JsonStatus.SUCCESS);
		ModelAndView mv = new ModelAndView();
		// mv.addObject("response", dto);
		mv.setViewName("houseDetail");
		return mv;
	}

	@RequestMapping(value = "/searchPeopleForHouse", method = RequestMethod.POST)
	public @ResponseBody
	GridJsonResponseBean searchForHouseMember(
			@RequestBody GridPeopleRequestBean requestBean) {
		return service.searchPeopleForHouse(requestBean);
	}

	@RequestMapping(value = "/saveMemberDetail", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResultBean saveMemberDetail(
			@RequestBody List<PeopleDomainBean> memberList) {
		return service.saveMemberRelation(memberList);
	}

	@RequestMapping(value = "/miniPeopleList/nohouse", method = RequestMethod.GET)
	public @ResponseBody
	List<PeopleMiniDomainBean> getMiniPeopleBeanListWithNoHouse() {
		return service.getMiniPeopleBeanListWithNoHouse();
	}

	@RequestMapping(value = "/editPeople/{pid}", method = RequestMethod.GET)
	public ModelAndView updatePeople(@PathVariable("pid") Integer pid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editPeople");
		mv.addObject("people", service.getPeople(pid));
		return mv;
	}

	@RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
	public @ResponseBody
	PictureUploadBean uploadHousePhotos(
			@RequestParam("Filedata") MultipartFile multipartFile,
			@RequestParam("Filename") String fileName,
			@RequestParam("pid") Integer pid) throws IllegalStateException,
			IOException {

		StringBuffer pathDir = new StringBuffer()
				.append(InforPropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.PEOPLE_IMAGE));

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH");
		String newName = new StringBuffer().append(UUID.randomUUID())
				.append(".").append(dateformat.format(new Date())).toString();// UUID.YYYY-MM-DD-HH
		// String originalName = multipartFile.getOriginalFilename();
		String pathAndName = pathDir.append(newName).toString();// F:\\upload\\people\\pictures\\UUID.YYYY-MM-DD-HH
		String[] nameExtPair = fileName.split(".");
		String originalExtension = nameExtPair.length > 1 ? "."
				+ nameExtPair[1] : ".png";
		File file = new File(pathAndName + originalExtension);
		multipartFile.transferTo(file);
		service.savePeoplePictureThumbnail(file, newName);
		service.updatePeoplePicture(pid, newName);
		PictureUploadBean jsonResult = new PictureUploadBean();
		jsonResult.setStatus(JsonStatus.SUCCESS);
		jsonResult.setFileName(newName);
		return jsonResult;
	}

	@RequestMapping(value = "/thumbnail", method = RequestMethod.GET)
	public void download(@RequestParam("file") String fileName,
			HttpServletResponse response) throws Exception {
		String path = new StringBuffer()
				.append(InforPropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.PEOPLE_IMAGE_THUMBNAIL))
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

	@RequestMapping(value = "/deletePicture", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean deletePicture(@RequestBody PeopleMiniDomainBean people) {
		service.deletePicture(people.getPid());
		JsonResultBean result = new JsonResultBean();
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/saveTextDetail", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean saveTextDetail(@RequestBody PeopleDomainBean people) {
		service.saveTextDetail(people);
		JsonResultBean result = new JsonResultBean();
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/saveSelectDetail", method = RequestMethod.POST)
	public @ResponseBody
	JsonResultBean saveSelectDetail(@RequestBody PeopleDomainBean people) {
		service.saveTextDetail(people);
		JsonResultBean result = new JsonResultBean();
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	PeopleMiniDomainBean createPeople(@RequestBody PeopleMiniDomainBean people) {
		PeopleMiniDomainBean result = new PeopleMiniDomainBean();
		result.setPid(service.createPeople(people.getName()));
		result.setStatus(JsonStatus.SUCCESS);
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResultBean deletePeople(@RequestBody PeopleMiniDomainBean people) {
		return service.deletePeople(people.getPid());
	}
}
