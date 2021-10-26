package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.LotDtoForAdmin;
import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import org.springframework.data.domain.Page;

public interface LotService {
  Lot findById(Long id);

  Page<LotDtoForAdmin> findAll(LotPage lotPage);

  Page<LotDtoForUsers> findAllNotDeleted(LotPage lotPage);

  Lot saveOrUpdateLot(Lot Lot);

  Long softDelete(Long id);


}
