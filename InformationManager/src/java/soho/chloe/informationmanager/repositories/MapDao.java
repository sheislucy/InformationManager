package soho.chloe.informationmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soho.chloe.informationmanager.entity.MapEntity;

public interface MapDao extends JpaRepository<MapEntity, Integer> {
	@Transactional(readOnly = true)
	public MapEntity findById(@Param("id") Integer id);
}
