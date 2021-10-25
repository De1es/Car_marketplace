package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.entity.Lot;
import by.delesevich.car_marketplace.entity.LotPage;
import by.delesevich.car_marketplace.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotRestController {

  private final LotService lotService;

  @GetMapping("/")
  public ResponseEntity <Page<Lot>> findAllLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAll(lotPage), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<Lot> createOrUpdateLot(@RequestBody @Valid Lot lot) {
    return new ResponseEntity<>(lotService.saveOrUpdateLot(lot), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Lot> findLotById(@PathVariable Long id) {
    return new ResponseEntity<>(lotService.findById(id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteLot(@PathVariable Long id){
    lotService.softDelete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
