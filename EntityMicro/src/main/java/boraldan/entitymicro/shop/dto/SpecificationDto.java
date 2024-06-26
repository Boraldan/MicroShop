package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpecificationDto {
    private CategoryName categoryName;

    // поиск по базовым полям Item
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String partName;

    // поля PageRequest
    private Integer page;
    private Integer pageSize;
    private String sortByPrice;

}
