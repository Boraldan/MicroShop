package boraldan.entitymicro.specification;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpecItem {
    private CategoryName categoryName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String partName;
}
