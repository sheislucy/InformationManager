/**
 * 
 */
package org.informationManager.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.informationManager.dto.HouseMemberRequestDTO;
import org.informationManager.dto.JsonRequestDTO;
import org.informationManager.dto.PeopleDomain;
import org.informationManager.dto.PeopleRequestDTO;
import org.informationManager.dto.PeopleResponseDTO;
import org.informationManager.entity.PeopleEntity;
import org.informationManager.repositories.PeopleDao;
import org.informationManager.utils.InformationManagerConstants;
import org.informationManager.utils.MyPropertyPlaceholderConfigurer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author sony
 * 
 */
@Service
public class PeopleServiceImpl extends BaseService implements PeopleService {

	@Autowired
	private PeopleDao dao;

	public PeopleResponseDTO getHouseMembers(HouseMemberRequestDTO requestDTO) {

		final HouseMemberRequestDTO dtoTemp = requestDTO;
		List<PeopleEntity> memberList = dao
				.findAll(buildSpecificationForHouse(dtoTemp));
		PeopleResponseDTO dto = new PeopleResponseDTO();
		dto.setPeopleList(buildResponse(memberList));
		return dto;
	}

	private Specification<PeopleEntity> buildSpecificationForHouse(
			final HouseMemberRequestDTO requestDTO) {
		Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
			@Override
			public Predicate toPredicate(Root<PeopleEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(cb.equal(root.<String> get("hostId"), 227));
				predicates.add(cb.equal(root.<String> get("pid"), 227));
				return cb
						.or(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
		return spec;
	}

	public PeopleResponseDTO getHostList(PeopleRequestDTO requestDTO) {
		if (requestDTO.getRows() <= 0) {
			requestDTO
					.setRows(Integer.parseInt((String) MyPropertyPlaceholderConfigurer
							.getContextProperty(InformationManagerConstants.DEFAULT_PAGE_SIZE)));
		}
		int totalCount = 0;
		List<PeopleEntity> peopleList = new ArrayList<PeopleEntity>();

		final PeopleRequestDTO dto1 = requestDTO;
		totalCount = (int) dao.count(buildSpecification(dto1));
		peopleList.addAll(dao.findAll(buildSpecification(dto1),
				buildPageRequest(requestDTO)).getContent());

		int rowsInPage = requestDTO.getRows();
		PeopleResponseDTO dto = new PeopleResponseDTO();
		dto.setTotal(totalCount / rowsInPage + 1);
		dto.setRecords(totalCount);
		dto.setPeopleList(buildResponse(peopleList));
		return dto;
	}

	private Specification<PeopleEntity> buildSpecification(
			final PeopleRequestDTO requestDTO) {
		Specification<PeopleEntity> spec = new Specification<PeopleEntity>() {
			@Override
			public Predicate toPredicate(Root<PeopleEntity> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(requestDTO.getSearchName())) {
					predicates.add(cb.like(root.<String> get("lname"), "%"
							+ requestDTO.getSearchName() + "%"));
				}

				if ("male".equalsIgnoreCase(requestDTO.getSearchGender())) {
					predicates.add(cb.equal(root.<String> get("gender"), 1));
				} else if ("female".equalsIgnoreCase(requestDTO
						.getSearchGender())) {
					predicates.add(cb.equal(root.<String> get("gender"), 0));
				}

				// look up for all host
				predicates.add(cb.equal(root.<String> get("pid"),
						root.<String> get("hostId")));

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

	private List<PeopleDomain> buildResponse(List<PeopleEntity> entityList) {
		List<PeopleDomain> domainList = new ArrayList<PeopleDomain>();
		for (PeopleEntity pe : entityList) {
			PeopleDomain pd = new PeopleDomain();

			BeanUtils.copyProperties(pe, pd);
			pd.setSortText(pe.getSortText());
			pd.setSocialText(pe.getSocialText());
			pd.setHostName(pe.getHostName());

			domainList.add(pd);
		}
		return domainList;
	}

	private PageRequest buildPageRequest(JsonRequestDTO requestDTO) {
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
