package soho.chloe.informationmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import soho.chloe.informationmanager.entity.GeoPointEntity;

public interface GeoPointDao extends JpaRepository<GeoPointEntity, Integer> {

}
