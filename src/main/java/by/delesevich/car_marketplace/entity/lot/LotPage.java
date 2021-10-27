package by.delesevich.car_marketplace.entity.lot;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class LotPage {
  @ApiModelProperty(
      value = "Number of current page",
      dataType = "Integer",
      example = "0"
  )
  private int pageNumber = 0;

  @ApiModelProperty(
      value = "Single page size",
      dataType = "Integer",
      example = "5"
  )
  private int pageSize = 5;

  @ApiModelProperty(
      value = "Direction of sorting"
  )
  private Sort.Direction sortDirection = Sort.Direction.ASC;

  @ApiModelProperty(
      value = "Parameter by which sorting will be performed"
  )
  private LotSortParam sortBy = LotSortParam.price;

}
