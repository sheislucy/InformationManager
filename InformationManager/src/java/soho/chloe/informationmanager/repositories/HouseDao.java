package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soho.chloe.informationmanager.entity.HouseEntity;

public interface HouseDao extends JpaRepository<HouseEntity, Integer> {

	@Query("FROM HouseEntity h WHERE NOT EXISTS( SELECT gp.houseId FROM GeoPointEntity gp WHERE h.id = gp.houseId )")
	List<HouseEntity> getUnmarkedHouse();

	long count();
}
