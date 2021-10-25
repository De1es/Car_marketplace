package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.Lot;
import by.delesevich.car_marketplace.entity.LotPage;
import by.delesevich.car_marketplace.repository.LotRepository;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Data
public class LotServiceImpl implements LotService{

  private final LotRepository lotRepository;

  @Override
  @Transactional
  public Lot findById(Long id) {
    Optional<Lot> optional = lotRepository.findById(id);
    if (optional.isEmpty()){
      throw new IllegalArgumentException("Lot with the given id was not found");
    }
    return optional.get();
  }

  @Override
  @Transactional
  public Page<Lot> findAll(LotPage lotPage) {
    Sort sort = Sort.by(lotPage.getSortDirection(), lotPage.getSortBy());
    Pageable pageable = PageRequest.of(lotPage.getPageNumber(), lotPage.getPageSize(), sort);
    return lotRepository.findAll(pageable);
  }

  @Override
  @Transactional
  public Lot saveOrUpdateLot(Lot lot) {
    return lotRepository.save(lot);
  }

  @Override
  @Transactional
  public void softDelete(Long id){
    findById(id);
    lotRepository.softDelete(id);
  }
}
