package by.delesevich.car_marketplace.entity.lot;

import by.delesevich.car_marketplace.entity.user.User;
import by.delesevich.car_marketplace.entity.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "lots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "l_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "l_seller_id", nullable = false)
  private User seller;

  @ManyToOne
  @JoinColumn(name = "l_vehicle_id", nullable = false)
  private Vehicle vehicle;

  @Column(name = "l_title")
  @Size(min = 10, max = 255, message = "Title should contain from 10 to 255 chars")
  private String title;

  @Column(name = "l_description")
  @Size(min = 10, max = 5000, message = "Title should contain from 10 to 5000 chars")
  private String description;

  @Column(name = "l_manufacture_date")
  @Past(message = "Manufacture date should not be in the future")
  private Timestamp manufactureDate;

  @Column(name = "l_price")
  @Positive(message = "The price must be a positive number")
  private Integer price;

  @Column(name = "l_date_of_sale")
  @Past(message = "Date of sale should not be in the future")
  private Timestamp dateOfSale;
}
