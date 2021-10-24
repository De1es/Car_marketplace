package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.Lot;

import java.util.List;
import java.util.Optional;

public interface LotService {
  Optional<Lot> findById(Long id);

  List<Lot> findAll();

  Lot saveOrUpdateLot(Lot Lot);

  void delete (Long id);


}
