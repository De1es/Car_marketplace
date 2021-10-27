package by.delesevich.car_marketplace.controller;

import by.delesevich.car_marketplace.config.SwaggerConfig;
import by.delesevich.car_marketplace.dto.LotDtoForUsers;
import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.lot.LotPage;
import by.delesevich.car_marketplace.service.LotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "LotAdminRestController", tags = {SwaggerConfig.USER_LOT_TAG})
@RequestMapping("/api/lots")
@RequiredArgsConstructor
public class LotRestController {

  private final LotService lotService;

  @GetMapping("/")
  @Operation(summary = "Find in db and return page of lots (exclude all deleted lots)")
  public ResponseEntity<Page<LotDtoForUsers>> findLots(LotPage lotPage) {
    return new ResponseEntity<>(lotService.findAllNotDeleted(lotPage), HttpStatus.OK);
  }

  @PostMapping("/")
  @Operation(summary = "Create if lot with this id not find in db, and update otherwise")
  public ResponseEntity<LotDtoForUsers> createOrUpdateLot(@RequestBody @Valid LotDtoForUsers dto) {
    Lot lot = lotService.saveOrUpdateLot(dto.toLot());
    return new ResponseEntity<>(new LotDtoForUsers(lot), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find and return a lot by id from db")
  public ResponseEntity<LotDtoForUsers> findLotById(@PathVariable @ApiParam(value = "Id of " +
      "finding lot", example = "1") Long id) {
    return new ResponseEntity<>(new LotDtoForUsers(lotService.findById(id)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Soft delete a lot by id in db")
  public ResponseEntity<Long> deleteLot(@PathVariable @ApiParam(value = "Id of " +
      "deleting lot", example = "1") Long id) {
    return new ResponseEntity<>(lotService.softDelete(id), HttpStatus.OK);
  }
}
