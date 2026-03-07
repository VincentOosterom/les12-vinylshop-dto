package nl.les12vinylshopdto.les12vinylshopdto.repositories;

import nl.les12vinylshopdto.les12vinylshopdto.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

}
