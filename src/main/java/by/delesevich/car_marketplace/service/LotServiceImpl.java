package by.delesevich.car_marketplace.service;

import by.delesevich.car_marketplace.dto.LotDtoForAdmin;
import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.repository.LotRepository;
import lombok.Data;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class LotServiceImpl implements LotService {

  private final LotRepository lotRepository;

  @Override
  @Transactional
  public Lot findById(Long id) {
    Optional<Lot> optional = lotRepository.findById(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("Lot with the given id was not found");
    }
    return optional.get();
  }

  @Override
  @Transactional
  public Page<LotDtoForUsers> findAllNotDeleted(LotPage lotPage) {
    Sort sort = Sort.by(lotPage.getSortDirection(), lotPage.getSortBy().toString());
    Pageable pageable = PageRequest.of(lotPage.getPageNumber(), lotPage.getPageSize(), sort);
    Page<Lot> page = lotRepository.findAllNotDeleted(pageable);
    List<LotDtoForUsers> list =
        page.getContent()
            .stream()
            .map(LotDtoForUsers::new)
            .collect(Collectors.toList());
    return new PageImpl<>(list, pageable, page.getTotalElements());
  }

  @Override
  @Transactional
  public Page<LotDtoForAdmin> findAll(LotPage lotPage) {
    Sort sort = Sort.by(lotPage.getSortDirection(), lotPage.getSortBy().toString());
    Pageable pageable = PageRequest.of(lotPage.getPageNumber(), lotPage.getPageSize(), sort);
    Page<Lot> page = lotRepository.findAll(pageable);
    List<LotDtoForAdmin> list =
        page.getContent()
            .stream()
            .map(LotDtoForAdmin::new)
            .collect(Collectors.toList());
    return new PageImpl<>(list, pageable, page.getTotalElements());
  }

  @Override
  @Transactional
  public Lot saveOrUpdateLot(Lot lot) {
    return lotRepository.save(lot);
  }

  @Override
  @Transactional
  public Long softDelete(Long id) {
    Optional<Lot> optional = lotRepository.findById(id);
    if (optional.isEmpty()) {
      throw new IllegalArgumentException("Lot with the given id was not found");
    }
    lotRepository.softDelete(id);
    return id;
  }

}
