package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByIdAndAlbumId(Long id, Long albumId);

    void deleteByIdAndAlbumId(Long id, Long albumId);

    List<StockEntity> findByAlbumId(Long albumId);
}
