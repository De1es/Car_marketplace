package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.entity.Lot;
import by.delesevich.car_marketplace.repository.LotRepository;
import lombok.Data;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class LotServiceImpl implements LotService{

  private final LotRepository lotRepository;

  @Override
  @Transactional
  public Optional<Lot> findById(Long id) {
    return lotRepository.findById(id);
  }

  @Override
  @Transactional
  public List<Lot> findAll() {
    return lotRepository.findAll();
  }

  @Override
  @Transactional
  public Lot saveOrUpdateLot(Lot lot) {
    return lotRepository.save(lot);
  }

  @Override
  @Transactional
  public void delete(Long id){
    lotRepository.delete(id);
  }
}
