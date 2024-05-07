package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpecificationDto {

    private BigDecimal minScore;
    private BigDecimal maxScore;
    private String partName;
    private Integer page;
    private CategoryName categoryName;

}
