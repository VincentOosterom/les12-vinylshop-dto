package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findArtistsByAlbumsId(@Param("albumId") Long albumId);
}
