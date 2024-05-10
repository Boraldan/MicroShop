package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpecificationDto {

    private Integer page;
    private Integer pageSize;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String partName;
    private CategoryName categoryName;

}
