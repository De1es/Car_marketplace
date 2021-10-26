package by.delesevich.car_marketplace.dto;

import by.delesevich.car_marketplace.entity.lot.Lot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class LotDtoForAdmin extends LotDtoForUsers {

  @PastOrPresent (message = "Date of sale should not be in the future")
  private Timestamp dateOfSale;


  @Override
  public Lot toLot() {
    Lot lot = super.toLot();
    lot.setDateOfSale(dateOfSale);
    return lot;
  }

  public LotDtoForAdmin(Lot lot) {
    super(lot);
    this.dateOfSale = lot.getDateOfSale();
  }
}
