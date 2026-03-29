package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    boolean existsByKcid(String kcid);
    Optional<ProfileEntity> findByKcid(String kcid);
}
