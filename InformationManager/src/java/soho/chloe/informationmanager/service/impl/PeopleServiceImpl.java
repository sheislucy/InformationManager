/**
 * 
 */
package soho.chloe.informationmanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
			final GridPeopleRequestBean requestDTO) {
		if (requestDTO.getRows() <= 0) {
			requestDTO
					.setRows(Integer.parseInt((String) MyPropertyPlaceholderConfigurer
							.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE)));
		}
		int totalCount = 0;
		List<PeopleEntity> peopleList = new ArrayList<PeopleEntity>();

		totalCount = (int) dao.count(buildSpecification(requestDTO));
		peopleList.addAll(dao.findAll(buildSpecification(requestDTO),
				buildPageRequest(requestDTO)).getContent());

		int rowsInPage = requestDTO.getRows();
		GridJsonResponseBean dto = new GridJsonResponseBean();
		dto.setTotal(totalCount / rowsInPage + 1);
		dto.setRecords(totalCount);
		buildResponse(dto, peopleList);
		return dto;
	}

	private Specification<PeopleEntity> buildSpecification(
			final GridPeopleRequestBean requestDTO) {
		Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
			@Override
			public Predicate toPredicate(Root<PeopleEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(requestDTO.getSearchName())) {
					predicates.add(cb.like(root.<String> get("name"), "%"
							+ requestDTO.getSearchName() + "%"));
				}

				if ("male".equalsIgnoreCase(requestDTO.getSearchGender())) {
					predicates.add(cb.equal(root.<String> get("gender"), 1));
				} else if ("female".equalsIgnoreCase(requestDTO
						.getSearchGender())) {
					predicates.add(cb.equal(root.<String> get("gender"), 0));
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

	private void buildResponse(GridJsonResponseBean dto,
			List<PeopleEntity> entityList) {
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

			dto.getRows().add(pd);
		}
	}

	private PageRequest buildPageRequest(GridJsonRequestBean requestDTO) {
		Sort sort = null;
		if (StringUtils.isEmpty(requestDTO.getSidx())) {
			sort = new Sort(Direction.DESC, "pid");
		} else if (StringUtils.isEmpty(requestDTO.getSord())
				|| InformationManagerConstants.SORT_ASC
						.equalsIgnoreCase(requestDTO.getSord())) {
			sort = new Sort(Direction.ASC,
					replaceSortColumnName(requestDTO.getSidx()));
		} else {
			sort = new Sort(Direction.DESC,
					replaceSortColumnName(requestDTO.getSidx()));
		}
		return new PageRequest(requestDTO.getPage() - 1, requestDTO.getRows(),
				sort);
	}

	private String replaceSortColumnName(String origin) {
		String replaced = null;
		if ("sortText".equalsIgnoreCase(origin)) {
			replaced = "psortId";
		} else if ("socialText".equalsIgnoreCase(origin)) {
			replaced = "psocialId";
		} else if ("hostName".equalsIgnoreCase(origin)) {
			replaced = "hostId";
		} else {
			replaced = origin;
		}
		return replaced;
	}

}
