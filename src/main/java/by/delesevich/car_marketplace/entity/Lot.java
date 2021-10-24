package by.delesevich.car_marketplace.entity;

import liquibase.pro.packaged.C;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lots")
@Data
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
  private String title;

  @Column(name = "l_description")
  private String description;

  @Column(name = "l_manufacture_date")
  private Timestamp manufactureDate;

  @Column(name = "l_price")
  private Integer price;

  @Column(name = "l_date_of_sale")
  private Timestamp dateOfSale;
}
