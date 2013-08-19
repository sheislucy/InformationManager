/**
 * 
 */
package soho.chloe.informationmanager.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.bean.ValidationResultBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.bean.PeopleMiniDomainBean;
import soho.chloe.informationmanager.entity.PeopleEntity;
import soho.chloe.informationmanager.enums.RelationEnum;
import soho.chloe.informationmanager.repositories.PeopleDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.PeopleService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.utils.JsonStatus;
import soho.chloe.informationmanager.web.InforPropertyPlaceholderConfigurer;

/**
 * @author sony
 * 
 */
@Service
public class PeopleServiceImpl extends BaseService implements PeopleService {

	@Autowired
	private PeopleDao dao;

	@Override
	public GridJsonResponseBean searchPeopleForHouse(GridPeopleRequestBean requestBean) {
		PageRequest page = new PageRequest(requestBean.getPage() - 1, requestBean.getRows(), new Sort(Direction.ASC,
				"pid"));
		List<PeopleEntity> entityList = dao.findByNameLikeAndHouseIdIsNull(requestBean.getName() + "%", page);
		int total = (int) dao.countByNameLikeAndHouseIdIsNull(requestBean.getName() + "%");
		GridJsonResponseBean responseBean = new GridJsonResponseBean();
		for (PeopleEntity entity : entityList) {
			responseBean.getRows().add(buildPeopleMiniDomainBean(entity));
		}
		int rowsInPage = requestBean.getRows();
		responseBean.setTotal(total / rowsInPage + 1);
		responseBean.setRecords(total);
		return responseBean;
	}

	@Override
	public GridJsonResponseBean getPeopleList(final GridPeopleRequestBean requestBean) {
		if (requestBean.getRows() <= 0) {
			requestBean.setRows(Integer.parseInt((String) InforPropertyPlaceholderConfigurer
					.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE)));
		}
		int totalCount = 0;
		List<PeopleEntity> peopleList = new ArrayList<PeopleEntity>();

		totalCount = (int) dao.count(buildSpecification(requestBean));
		peopleList.addAll(dao.findAll(buildSpecification(requestBean), buildPageRequest(requestBean)).getContent());

		int rowsInPage = requestBean.getRows();
		GridJsonResponseBean response = new GridJsonResponseBean();
		response.setTotal(totalCount / rowsInPage + 1);
		response.setRecords(totalCount);
		buildResponse(response, peopleList);
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ValidationResultBean saveMemberRelation(List<PeopleDomainBean> memberList) {
		ValidationResultBean result = new ValidationResultBean();
		int hostCount = 0;
		int spouseCount = 0;
		boolean sameHouse = true;
		Integer houseId = null;
		for (PeopleDomainBean people : memberList) {
			int relation = Integer.parseInt(people.getRelation());
			if (relation == 1) {
				hostCount++;
			} else if (relation == 2) {
				spouseCount++;
			}
			if (houseId == null) {
				houseId = people.getHouseId();
			} else if (people.getHouseId() != people.getHouseId()) {
				sameHouse = false;
				break;
			}
		}
		if (sameHouse && houseId != null) {
			if (hostCount == 0) {
				result.setStatus(JsonStatus.ERROR);
				result.getErrorList().add("户主缺失");
			} else if (hostCount > 1) {
				result.setStatus(JsonStatus.ERROR);
				result.getErrorList().add("户主多于一个");
			}
			if (spouseCount > 1) {
				result.setStatus(JsonStatus.ERROR);
				result.getErrorList().add("配偶多于一个");
			}
			if (result.getErrorList().isEmpty()) {
				dao.clearSpecificHouseMembers(houseId);
				for (PeopleDomainBean people : memberList) {
					int relation = Integer.parseInt(people.getRelation());
					dao.updateSpecificHouseMembers(people.getPid(), people.getHouseId(), relation);
				}
				result.setStatus(JsonStatus.SUCCESS);
			}
		} else {
			result.setStatus(JsonStatus.ERROR);
			result.getErrorList().add("各成员未指定同一户");
		}
		return result;
	}

	@Override
	public List<PeopleMiniDomainBean> getMiniPeopleBeanListWithNoHouse() {
		List<PeopleEntity> entityList = dao.findByHouseIdIsNull();
		List<PeopleMiniDomainBean> beanList = new ArrayList<PeopleMiniDomainBean>();
		for (PeopleEntity entity : entityList) {
			beanList.add(buildPeopleMiniDomainBean(entity));
		}
		return beanList;
	}

	@Override
	public PeopleDomainBean getPeople(int pid) {
		return buildPeopleDomainBean(dao.findOne(pid));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updatePeoplePicture(int pid, String fileName) {
		PeopleEntity entity = dao.findOne(pid);
		entity.setPicture(fileName);
		dao.save(entity);
	}

	@Override
	public void savePeoplePictureThumbnail(File originalFile, String fileName) throws IOException {
		Thumbnails
				.of(originalFile)
				.size(120, 160)
				.outputFormat("png")
				.toFile(InforPropertyPlaceholderConfigurer
						.getContextProperty(InformationManagerConstants.PEOPLE_IMAGE_THUMBNAIL)
						+ fileName
						+ "_thumbnail");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deletePicture(int pid) {
		dao.deletePicture(pid);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveTextDetail(PeopleDomainBean people) {
		PeopleEntity entity = dao.findOne(people.getPid());
		entity.setCardId(people.getCardId());
		entity.setSname(people.getSname());
		entity.setPolitical(people.getPoliticalId());
		entity.setBirthday(people.getBirthday());
		entity.setAddr(people.getAddr());
		entity.setResidentId(people.getResidentId());
		entity.setJob(people.getJob());
		entity.setTel(people.getTel());
		entity.setPhone(people.getPhone());
		entity.setWplace(people.getWplace());
		entity.setSpec(people.getSpec());
		entity.setIncomeSource(people.getIncomeSource());
		entity.setEthnic(people.getEthnic());
		entity.setArmy(people.getArmy());
		entity.setHealth(people.getHealth());
		entity.setDiffCond(people.getDiffCond());
		entity.setCompanyName(people.getCompanyName());
		entity.setCurrentAddress(people.getCurrentAddress());
		dao.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void saveSelectDetail(PeopleDomainBean people) {
		PeopleEntity entity = dao.findOne(people.getPid());
		entity.setGender(people.getGenderId());
		entity.setEducation(people.getEducationId());
		entity.setPolitical(people.getPoliticalId());
		entity.setMarriageId(people.getMarriageId());
		entity.setPosition(people.getPositionId());
		entity.setResidentId(people.getResidentId());
		entity.setIsLowSafe(people.getIsLowSafe());
		entity.setIsaddsafe(people.getIsaddsafe());
		entity.setIsCorps(people.getIsCorps());
		entity.setIsOut(people.getIsOut());
		entity.setIsOverSea(people.getIsOverSea());
		dao.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public int createPeople(String name) {
		PeopleEntity entity = new PeopleEntity();
		entity.setName(name);
		entity = dao.save(entity);
		return entity.getPid();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public ValidationResultBean deletePeople(int pid) {
		PeopleEntity people = dao.findOne(pid);
		ValidationResultBean result = new ValidationResultBean();
		if (people.getRelationId() != null && people.getRelationId() == RelationEnum.HOST.getCode()) {
			result.getErrorList().add("该人员是户主，请先解散他的家庭再将他删除");
			result.setStatus(JsonStatus.ERROR);
		} else {
			dao.delete(pid);
			result.setStatus(JsonStatus.SUCCESS);
		}
		return result;
	}

	private PeopleMiniDomainBean buildPeopleMiniDomainBean(PeopleEntity entity) {
		PeopleMiniDomainBean bean = new PeopleMiniDomainBean();
		bean.setBirthday(entity.getBirthday());
		bean.setCardId(entity.getCardId());
		if (entity.getGender() == null) {
			bean.setGender("未知");
		} else if (entity.getGender() == 1) {
			bean.setGender("男");
		} else {
			bean.setGender("女");
		}
		bean.setHouseId(entity.getHouseId());
		bean.setName(entity.getName());
		bean.setPid(entity.getPid());
		return bean;
	}

	private Specification<PeopleEntity> buildSpecification(final GridPeopleRequestBean requestBean) {
		Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
			@Override
			public Predicate toPredicate(Root<PeopleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(requestBean.getName())) {
					predicates.add(cb.like(root.<String> get("name"), "%" + requestBean.getName() + "%"));
				}

				if (!StringUtils.isEmpty(requestBean.getGender())) {
					predicates.add(cb.equal(root.<Boolean> get("gender"), Boolean.valueOf(requestBean.getGender())));
				}

				Calendar calendar = Calendar.getInstance();
				if (!StringUtils.isEmpty(requestBean.getAgeUp())) {
					Integer ageLow = Integer.parseInt(requestBean.getAgeUp());
					calendar.setTime(new Date());
					calendar.add(Calendar.YEAR, -ageLow);
					Date startDate = (Date) calendar.getTime().clone();
					predicates.add(cb.greaterThanOrEqualTo(root.<Date> get("birthday"), startDate));
					System.out.println("startDate: " + startDate);
				}

				if (!StringUtils.isEmpty(requestBean.getAgeLow())) {
					Integer ageUp = Integer.parseInt(requestBean.getAgeLow());
					calendar.setTime(new Date());
					calendar.add(Calendar.YEAR, -ageUp);
					Date endDate = (Date) calendar.getTime().clone();
					predicates.add(cb.lessThanOrEqualTo(root.<Date> get("birthday"), endDate));
					System.out.println("endDate: " + endDate);
				}

				if (!StringUtils.isEmpty(requestBean.getIncomeLow())) {
					Integer incomeLow = Integer.parseInt(requestBean.getIncomeLow());
					predicates.add(cb.greaterThanOrEqualTo(root.<Integer> get("yearIncome"), incomeLow));
				}

				if (!StringUtils.isEmpty(requestBean.getIncomeUp())) {
					Integer incomeUp = Integer.parseInt(requestBean.getIncomeUp());
					predicates.add(cb.lessThanOrEqualTo(root.<Integer> get("yearIncome"), incomeUp));
				}

				if (!requestBean.getPolitical().isEmpty()) {
					predicates.add(root.<Integer> get("political").in(requestBean.getPolitical()));
				}

				if (!requestBean.getEducation().isEmpty()) {
					predicates.add(root.<Integer> get("education").in(requestBean.getEducation()));
				}

				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				}
				return cb.conjunction();
			}
		};
		return spec;
	}

	private void buildResponse(GridJsonResponseBean response, List<PeopleEntity> entityList) {
		response.getRows().clear();
		for (PeopleEntity pe : entityList) {
			response.getRows().add(buildPeopleDomainBean(pe));
		}
	}

	private PeopleDomainBean buildPeopleDomainBean(PeopleEntity pe) {
		PeopleDomainBean pd = new PeopleDomainBean();

		pd.setAddr(pe.getAddr());
		pd.setArmy(pe.getArmy());
		pd.setBirthday(pe.getBirthday());
		pd.setCardId(pe.getCardId());
		pd.setCompanyName(pe.getCompanyName());
		pd.setCurrentAddress(pe.getCurrentAddress());
		pd.setDiffCond(pe.getDiffCond());
		pd.setEducationId(pe.getEducation());
		pd.setEducation(pe.getEducationType() != null ? pe.getEducationType().getTypeName() : null);
		pd.setEthnic(pe.getEthnic());
		pd.setGenderId(pe.getGender());
		if (pe.getGender() == null) {
			pd.setGender("未知");
		} else if (pe.getGender() == 1) {
			pd.setGender("男");
		} else {
			pd.setGender("女");
		}
		pd.setHealth(pe.getHealth());
		pd.setHeight(pe.getHeight());
		pd.setRelationId(pe.getRelationId());
		pd.setRelation(pe.getRelationType() != null ? pe.getRelationType().getRelationName() : null);
		pd.setIncomeSource(pe.getIncomeSource());
		pd.setIsaddsafe(pe.getIsaddsafe());
		pd.setIsCorps(pe.getIsCorps());
		pd.setIsLowSafe(pe.getIsLowSafe());
		pd.setIsOut(pe.getIsOut());
		pd.setIsOverSea(pe.getIsOverSea());
		pd.setJob(pe.getJob());
		pd.setLastUpdateTime(pe.getLastUpdateTime());
		pd.setMarriageId(pe.getMarriageId());
		pd.setMarriage(pe.getMarriageType() != null ? pe.getMarriageType().getTypeName() : null);
		pd.setMemo(pe.getMemo());
		pd.setName(pe.getName());
		pd.setPhone(pe.getPhone());
		pd.setPid(pe.getPid());
		pd.setPoliticalId(pe.getPolitical());
		pd.setPolitical(pe.getPoliticalType() != null ? pe.getPoliticalType().getPolitical() : null);
		pd.setPositionId(pe.getPosition());
		pd.setPosition(pe.getPositionType() != null ? pe.getPositionType().getPosition() : null);
		pd.setPtype(pe.getMobilityType() != null ? pe.getMobilityType().getTypeName() : null);
		pd.setRelationId(pe.getRelationId());
		pd.setRelation(pe.getRelationType() != null ? pe.getRelationType().getRelationName() : null);
		pd.setResidentId(pe.getResidentId());
		pd.setResident(pe.getResidentType() != null ? pe.getResidentType().getTypeName() : null);
		pd.setSname(pe.getSname());
		pd.setSocialId(pe.getSocial());
		pd.setSocial(pe.getSocialType() != null ? pe.getSocialType().getSocial() : null);
		pd.setSpec(pe.getSpec());
		pd.setTel(pe.getTel());
		// pd.setVillage(pe.getVillageId());
		pd.setWplace(pe.getWplace());
		pd.setYearIncome(pe.getYearIncome());
		pd.setPicture(pe.getPicture());
		return pd;
	}

	private PageRequest buildPageRequest(GridJsonRequestBean requestBean) {
		Sort sort = null;
		if (StringUtils.isEmpty(requestBean.getSidx()) && StringUtils.isEmpty(requestBean.getSord())) {
			sort = new Sort(Direction.DESC, "pid");
		} else if (StringUtils.isEmpty(requestBean.getSidx())) {
			if (requestBean.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, "pid");
			} else {
				sort = new Sort(Direction.ASC, "pid");
			}
		} else if (StringUtils.isEmpty(requestBean.getSord())) {
			sort = new Sort(Direction.DESC, requestBean.getSidx());
		} else {
			if (requestBean.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, requestBean.getSidx());
			} else {
				sort = new Sort(Direction.ASC, requestBean.getSidx());
			}
		}

		return new PageRequest(requestBean.getPage() - 1, requestBean.getRows(), sort);
	}

}
