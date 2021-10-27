package by.delesevich.car_marketplace.controller.admin;

import by.delesevich.car_marketplace.config.SwaggerConfig;
import by.delesevich.car_marketplace.dto.LotDtoForAdmin;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.service.LotService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "LotAdminRestController", tags = {SwaggerConfig.ADMIN_LOT_TAG})
@RequestMapping("/api/admin/lots")
@RequiredArgsConstructor
public class LotAdminRestController {

  private final LotService lotService;

  @GetMapping("/")
  @Operation(summary = "Find in db and return page of lots (include all deleted lots)")
  public ResponseEntity<Page<LotDtoForAdmin>> findAllLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAll(lotPage), HttpStatus.OK);
  }

}
