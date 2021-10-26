package by.delesevich.car_marketplace.entity.vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "v_id")
  private Long id;

  @Column(name = "v_make")
  private String make;

  @Column(name = "v_model")
  private String model;

  public Vehicle(Long id) {
    this.id = id;
  }
}
