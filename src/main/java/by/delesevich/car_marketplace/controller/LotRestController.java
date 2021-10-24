package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.entity.Lot;
import by.delesevich.car_marketplace.entity.LotPage;
import by.delesevich.car_marketplace.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotRestController {

  private final LotService lotService;

  @GetMapping("/")
  public Page<Lot> findAllLots(LotPage lotPage) {
    return lotService.findAll(lotPage);
  }

  @PostMapping("/")
  public Lot createOrUpdateLot(@RequestBody Lot lot) {
    return lotService.saveOrUpdateLot(lot);
  }

  @GetMapping("/{id}")
  public Optional<Lot> findLotById(@PathVariable Long id) {
    return lotService.findById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteLot(@PathVariable Long id){
    lotService.delete(id);
  }
}
