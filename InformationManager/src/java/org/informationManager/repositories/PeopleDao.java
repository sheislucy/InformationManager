package org.informationManager.repositories;

import org.informationManager.entity.PeopleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleDao extends JpaRepository<PeopleEntity, Integer> {

	@Transactional(readOnly = true)
	public Page<PeopleEntity> findAll(Pageable pageable);

	@Transactional(readOnly = true)
	public Page<PeopleEntity> findAll(Specification<PeopleEntity> spec, Pageable pageable);
	
	@Transactional(readOnly = true)
	public long count(Specification<PeopleEntity> spec);

	@Transactional(readOnly = true)
	public PeopleEntity findByPid(@Param("id") Integer id);

	public void delete(@Param("pid") Integer id);

}
