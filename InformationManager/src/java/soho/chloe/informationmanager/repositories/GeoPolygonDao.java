package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soho.chloe.informationmanager.entity.GeoPolygonEntity;

/**
 * @author lucy
 *
 */
public interface GeoPolygonDao extends JpaRepository<GeoPolygonEntity, Integer> {
	@Transactional(readOnly = true)
	public List<GeoPolygonEntity> findByMapId(@Param("mapId") Integer mapId);
}
