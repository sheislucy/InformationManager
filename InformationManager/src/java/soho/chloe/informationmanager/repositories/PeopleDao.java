package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import soho.chloe.informationmanager.entity.PeopleEntity;

public interface PeopleDao extends JpaRepository<PeopleEntity, Integer> {

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PeopleEntity> findAll(Pageable pageable);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Page<PeopleEntity> findAll(Specification<PeopleEntity> spec,
			Pageable pageable);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long count(Specification<PeopleEntity> spec);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public PeopleEntity findByPid(@Param("id") Integer id);

	public void delete(@Param("pid") Integer id);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PeopleEntity> findByNameLikeAndHouseIdIsNull(String name,
			Pageable page);

	@Query("SELECT COUNT (*) FROM  PeopleEntity p WHERE p.name LIKE ?1 AND p.houseId IS NULL")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public long countByNameLikeAndHouseIdIsNull(String name);

	@Modifying
	@Query("UPDATE PeopleEntity p SET p.houseId =  NULL, p.relationId = NULL WHERE p.houseId = ?1")
	public void clearSpecificHouseMembers(Integer houseId);

	@Modifying
	@Query("UPDATE PeopleEntity p SET p.houseId =  ?2, p.relationId = ?3 WHERE p.pid = ?1")
	public void updateSpecificHouseMembers(Integer pid, Integer houseId,
			Integer relationId);

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PeopleEntity> findByHouseIdIsNull();
}
