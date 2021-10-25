package by.delesevich.car_marketplace.entity;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class UserPage {
  private int pageNumber = 0;
  private int pageSize = 5;
  private Sort.Direction sortDirection = Sort.Direction.ASC;
  private String sortBy = "id";
}
