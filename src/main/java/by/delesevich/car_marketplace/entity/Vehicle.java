package by.delesevich.car_marketplace.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "v_id")
  private Long id;

  @Column(name = "v_make")
  private String make;

  @Column(name = "v_model")
  private String model;

}
