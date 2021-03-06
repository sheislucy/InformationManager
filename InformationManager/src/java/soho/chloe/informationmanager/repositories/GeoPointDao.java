package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soho.chloe.informationmanager.entity.GeoPointEntity;

public interface GeoPointDao extends JpaRepository<GeoPointEntity, Integer> {

	@Transactional(readOnly = true)
	public List<GeoPointEntity> findByMapId(@Param("mapId") Integer mapId);
}
