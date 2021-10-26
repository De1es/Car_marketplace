package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.dto.LotDtoForAdmin;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/lots")
@RequiredArgsConstructor
public class LotAdminRestController {

  private final LotService lotService;

  @GetMapping("/")
  public ResponseEntity<Page<LotDtoForAdmin>> findAllLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAll(lotPage), HttpStatus.OK);
  }

}
