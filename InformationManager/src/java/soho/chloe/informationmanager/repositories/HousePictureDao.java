package soho.chloe.informationmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import soho.chloe.informationmanager.entity.HousePictureEntity;

public interface HousePictureDao extends
		JpaRepository<HousePictureEntity, Integer> {
	List<HousePictureEntity> findByHouseId(Integer houseId);
}
