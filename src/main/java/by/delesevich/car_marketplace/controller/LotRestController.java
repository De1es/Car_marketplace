package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
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
  public ResponseEntity<Page<LotDtoForUsers>> findLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAllNotDeleted(lotPage), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<LotDtoForUsers> createOrUpdateLot(@RequestBody @Valid LotDtoForUsers dto) {
    Lot lot = lotService.saveOrUpdateLot(dto.toLot());
    return new ResponseEntity<>(new LotDtoForUsers(lot), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<LotDtoForUsers> findLotById(@PathVariable Long id) {
    return new ResponseEntity<>(new LotDtoForUsers(lotService.findById(id)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteLot(@PathVariable Long id) {
    return new ResponseEntity<>(lotService.softDelete(id), HttpStatus.OK);
  }
}
