package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.dto.SpecificationDto;
import boraldan.entitymicro.shop.entity.category.CategoryName;

import java.math.BigDecimal;

public class SpecificationDtoBuilder {

    private final SpecificationDto spec;

    private SpecificationDtoBuilder() {
        this.spec = new SpecificationDto();
    }

    public static SpecificationDtoBuilder creat() {
        return new SpecificationDtoBuilder();
    }

    public SpecificationDtoBuilder setPage(Integer page) {
        this.spec.setPage(page);
        return this;
    }

    public SpecificationDtoBuilder setPageSize(Integer pageSize) {
        if (pageSize == null) pageSize = 10;
        this.spec.setPageSize(pageSize);
        return this;
    }

    public SpecificationDtoBuilder setMinScore(Long minPrice) {
        if (minPrice == null) return this;
        this.spec.setMinPrice(BigDecimal.valueOf(minPrice));
        return this;
    }

    public SpecificationDtoBuilder setMaxScore(Long maxPrice) {
        if (maxPrice == null) return this;
        this.spec.setMaxPrice(BigDecimal.valueOf(maxPrice));
        return this;
    }

    public SpecificationDtoBuilder setPartName(String partName) {
        if (partName == null) return this;
        if (partName.isBlank()) return this;
        this.spec.setPartName(partName.strip());
        return this;
    }

    public SpecificationDtoBuilder setCategoryName(CategoryName categoryName) {
        this.spec.setCategoryName(categoryName);
        return this;
    }

    public SpecificationDto build() {
        return this.spec;
    }
}
