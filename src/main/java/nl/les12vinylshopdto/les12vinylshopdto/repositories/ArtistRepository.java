package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

}
