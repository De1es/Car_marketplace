package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import org.springframework.data.domain.Page;

public interface LotService {
  Lot findById(Long id);

  Page<Lot> findAll(LotPage lotPage);

  Page<LotDtoForUsers> findAllNotDeleted(LotPage lotPage);

  Lot saveOrUpdateLot(Lot Lot);

  void softDelete(Long id);


}
