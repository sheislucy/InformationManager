package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soho.chloe.informationmanager.entity.GeoLineEntity;

/**
 * @author lucy
 * 
 */
public interface GeoLineDao extends JpaRepository<GeoLineEntity, Integer> {
	@Transactional(readOnly = true)
	public List<GeoLineEntity> findByMapId(@Param("mapId") Integer mapId);
}
