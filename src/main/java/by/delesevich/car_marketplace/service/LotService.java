package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.Lot;
import by.delesevich.car_marketplace.entity.LotPage;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface LotService {
  Optional<Lot> findById(Long id);

  Page<Lot> findAll(LotPage lotPage);

  Lot saveOrUpdateLot(Lot Lot);

  void delete (Long id);


}
