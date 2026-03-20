package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

    List<AlbumEntity> findByStockItemsNotEmpty();
    List<AlbumEntity> findByStockItemsEmpty();
    List<AlbumEntity> findByGenre_Id(Long genreId);

}
