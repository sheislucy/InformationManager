/**
 * 
 */
package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import soho.chloe.informationmanager.bean.GridJsonRequestBean;
import soho.chloe.informationmanager.bean.GridJsonResponseBean;
import soho.chloe.informationmanager.bean.GridPeopleRequestBean;
import soho.chloe.informationmanager.bean.PeopleDomainBean;
import soho.chloe.informationmanager.entity.PeopleEntity;
import soho.chloe.informationmanager.repositories.PeopleDao;
import soho.chloe.informationmanager.service.BaseService;
import soho.chloe.informationmanager.service.PeopleService;
import soho.chloe.informationmanager.utils.InformationManagerConstants;
import soho.chloe.informationmanager.web.MyPropertyPlaceholderConfigurer;

/**
 * @author sony
 * 
 */
@Service
public class PeopleServiceImpl extends BaseService implements PeopleService {

	@Autowired
	private PeopleDao dao;

	// public PeopleResponseDTO getHouseMembers(HouseMemberRequestDTO
	// requestDTO) {
	//
	// final HouseMemberRequestDTO dtoTemp = requestDTO;
	// List<PeopleEntity> memberList = dao
	// .findAll(buildSpecificationForHouse(dtoTemp));
	// PeopleResponseDTO dto = new PeopleResponseDTO();
	// dto.setPeopleList(buildResponse(memberList));
	// return dto;
	// }
	//
	// private Specification<PeopleEntity> buildSpecificationForHouse(
	// final HouseMemberRequestDTO requestDTO) {
	// Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
	// @Override
	// public Predicate toPredicate(Root<PeopleEntity> root,
	// CriteriaQuery<?> query, CriteriaBuilder cb) {
	// List<Predicate> predicates = new ArrayList<Predicate>();
	// predicates.add(cb.equal(root.<String> get("hostId"), 227));
	// predicates.add(cb.equal(root.<String> get("pid"), 227));
	// return cb
	// .or(predicates.toArray(new Predicate[predicates.size()]));
	// }
	//
	// };
	// return spec;
	// }

	public GridJsonResponseBean getPeopleList(
			final GridPeopleRequestBean requestBean) {
		if (requestBean.getRows() <= 0) {
			requestBean
					.setRows(Integer.parseInt((String) MyPropertyPlaceholderConfigurer
							.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE)));
		}
		int totalCount = 0;
		List<PeopleEntity> peopleList = new ArrayList<PeopleEntity>();

		totalCount = (int) dao.count(buildSpecification(requestBean));
		peopleList.addAll(dao.findAll(buildSpecification(requestBean),
				buildPageRequest(requestBean)).getContent());

		int rowsInPage = requestBean.getRows();
		GridJsonResponseBean response = new GridJsonResponseBean();
		response.setTotal(totalCount / rowsInPage + 1);
		response.setRecords(totalCount);
		buildResponse(response, peopleList);
		return response;
	}

	private Specification<PeopleEntity> buildSpecification(
			final GridPeopleRequestBean requestDTO) {
		Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
			@Override
			public Predicate toPredicate(Root<PeopleEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(requestDTO.getName())) {
					predicates.add(cb.like(root.<String> get("name"), "%"
							+ requestDTO.getName() + "%"));
				}

				if (!StringUtils.isEmpty(requestDTO.getGender())) {
					predicates.add(cb.equal(root.<Boolean> get("gender"),
							requestDTO.getGender()));
				}

				if (!StringUtils.isEmpty(requestDTO.getAgeLow())) {
					Double ageLow = Double.parseDouble(requestDTO.getAgeLow());
//TODO					predicates.add(cb.lessThanOrEqualTo(root.<Date>get("birthday"), param)); 
				}

				// look up for all host
				// predicates.add(cb.equal(root.<String> get("pid"),
				// root.<String> get("hostId")));

				// if ("lt".equalsIgnoreCase(requestDTO.getSearchAgeRule())) {
				// Path<String> genderPath = root.get("idcard");
				// cb.substring(genderPath, 6, 8);
				// cb.
				// predicates.add(cb.substring(genderPath, 6, 8));
				// } else if
				// ("gt".equalsIgnoreCase(requestDTO.getSearchAgeRule())) {
				// }

				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates
							.size()]));
				}
				return cb.conjunction();
			}
		};
		return spec;
	}

	private void buildResponse(GridJsonResponseBean response,
			List<PeopleEntity> entityList) {
		response.getRows().clear();
		for (PeopleEntity pe : entityList) {
			PeopleDomainBean pd = new PeopleDomainBean();

			pd.setAddr(pe.getAddr());
			pd.setArmy(pe.getArmy());
			pd.setBirthday(pe.getBirthday());
			pd.setCardId(pe.getCardId());
			pd.setCompanyName(pe.getCompanyName());
			pd.setCurrentAddress(pe.getCurrentAddress());
			pd.setDiffCond(pe.getDiffCond());
			pd.setEducation(pe.getEducationType() != null ? pe
					.getEducationType().getTypeName() : null);
			pd.setEthnic(pe.getEthnic());
			pd.setGender(pe.getGender() ? "男" : "女");
			pd.setHealth(pe.getHealth());
			pd.setHeight(pe.getHeight());
			pd.setRelation(pe.getRelationType() != null ? pe.getRelationType()
					.getRelationName() : null);
			pd.setIncomeSource(pe.getIncomeSource());
			pd.setIsaddsafe(pe.getIsaddsafe());
			pd.setIsCorps(pe.getIsCorps());
			pd.setIsLowSafe(pe.getIsLowSafe());
			pd.setIsOut(pe.getIsOut());
			pd.setIsOverSea(pe.getIsOverSea());
			pd.setJob(pe.getJob());
			pd.setLastUpdateTime(pe.getLastUpdateTime());
			pd.setMarriage(pe.getMarriageType() != null ? pe.getMarriageType()
					.getTypeName() : null);
			pd.setMemo(pe.getMemo());
			pd.setName(pe.getName());
			pd.setPhone(pe.getPhone());
			pd.setPid(pe.getPid());
			pd.setPolitical(pe.getPoliticalType() != null ? pe
					.getPoliticalType().getPolitical() : null);
			pd.setPosition(pe.getPositionType() != null ? pe.getPositionType()
					.getPosition() : null);
			pd.setPtype(pe.getMobilityType() != null ? pe.getMobilityType()
					.getTypeName() : null);
			pd.setRelation(pe.getRelationType() != null ? pe.getRelationType()
					.getRelationName() : null);
			pd.setResident(pe.getResidentType() != null ? pe.getResidentType()
					.getTypeName() : null);
			pd.setSname(pe.getSname());
			pd.setSocial(pe.getSocialType() != null ? pe.getSocialType()
					.getSocial() : null);
			pd.setSpec(pe.getSpec());
			pd.setTel(pe.getTel());
			// pd.setVillage(pe.getVillageId());
			pd.setWplace(pe.getWplace());
			pd.setYearIncome(pe.getYearIncome());

			response.getRows().add(pd);
		}
	}

	private PageRequest buildPageRequest(GridJsonRequestBean requestDTO) {
		Sort sort = null;
		if (StringUtils.isEmpty(requestDTO.getSidx())
				&& StringUtils.isEmpty(requestDTO.getSord())) {
			sort = new Sort(Direction.DESC, "pid");
		} else if (StringUtils.isEmpty(requestDTO.getSidx())) {
			if (requestDTO.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, "pid");
			} else {
				sort = new Sort(Direction.ASC, "pid");
			}
		} else if (StringUtils.isEmpty(requestDTO.getSord())) {
			sort = new Sort(Direction.DESC, requestDTO.getSidx());
		} else {
			if (requestDTO.getSord().equalsIgnoreCase(Direction.DESC.name())) {
				sort = new Sort(Direction.DESC, requestDTO.getSidx());
			} else {
				sort = new Sort(Direction.ASC, requestDTO.getSidx());
			}
		}

		return new PageRequest(requestDTO.getPage() - 1, requestDTO.getRows(),
				sort);
	}

}
