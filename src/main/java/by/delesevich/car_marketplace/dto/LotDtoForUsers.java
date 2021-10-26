package by.delesevich.car_marketplace.dto;

import by.delesevich.car_marketplace.entity.lot.Lot;
import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class LotDtoForUsers {

  @PositiveOrZero(message = "Id should be positive number")
  private Long id;

  @NotNull(message = "Seller id should not be empty")
  private Long sellerId;

  private String sellerLogin;

  @NotNull(message = "Vehicle id should not be empty")
  private Long vehicleId;

  @Size(min = 2, max = 255, message = "Make field should contain from 10 to 255 chars")
  private String vehicleMake;

  @Size(min = 1, max = 255, message = "Model field should contain from 10 to 255 chars")
  private String vehicleModel;

  @Size(min = 10, max = 255, message = "Title should contain from 10 to 255 chars")
  private String title;

  @Size(min = 10, max = 5000, message = "Title should contain from 10 to 5000 chars")
  private String description;

  @Past(message = "Date of sale should not be in the future")
  private Date manufactureDate;

  @Positive(message = "The price must be a positive number")
  private Integer price;

  public static LotDtoForUsers of(Lot lot) {
    if (lot==null){
      return null;
    }
    return new LotDtoForUsers(lot.getId(), lot.getSeller().getId(), lot.getSeller().getLogin(),
        lot.getVehicle().getId(), lot.getVehicle().getMake(), lot.getVehicle().getModel(),
        lot.getTitle(), lot.getDescription(), new Date(lot.getManufactureDate().getTime()),
        lot.getPrice());
  }

  public Lot toLot () {
    return new Lot(id, new User(sellerId), new Vehicle(vehicleId), title,
        description, new Timestamp((manufactureDate).getTime()), price, null);
  }

}
