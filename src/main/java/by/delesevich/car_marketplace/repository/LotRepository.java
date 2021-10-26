package by.delesevich.car_marketplace.repository;

import by.delesevich.car_marketplace.entity.lot.Lot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

  @Modifying
  @Query("update Lot set dateOfSale = current_timestamp where id = :id")
  void softDelete(Long id);

  @Query("select l from Lot l where l.dateOfSale is null")
  Page<Lot> findAllNotDeleted(Pageable pageable);
}
