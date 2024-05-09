package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.dto.SpecificationDto;
import boraldan.entitymicro.shop.entity.category.CategoryName;

import java.math.BigDecimal;

public class SpecificationDtoBuilder {

    private final SpecificationDto spec;

    private SpecificationDtoBuilder() {
        this.spec = new SpecificationDto();
    }

    public static SpecificationDtoBuilder creat(){
        return new SpecificationDtoBuilder();
    }


    public SpecificationDtoBuilder setMinScore(Long minScore) {
        if (minScore == null) return this;
        this.spec.setMinScore(BigDecimal.valueOf(minScore));
        return this;
    }

    public SpecificationDtoBuilder setMaxScore(Long maxScore) {
        if (maxScore == null) return this;
        this.spec.setMaxScore(BigDecimal.valueOf(maxScore));
        return this;
    }

    public SpecificationDtoBuilder setPartName(String partName) {
        if (partName == null) return this;
        if (partName.isBlank()) return this;
        this.spec.setPartName(partName.strip());
        return this;
    }

    public SpecificationDtoBuilder setPage(Integer page) {
        this.spec.setPage(page);
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
