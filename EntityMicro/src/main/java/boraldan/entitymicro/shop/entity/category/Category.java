package boraldan.entitymicro.shop.entity.category;

import boraldan.entitymicro.shop.entity.item.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;

    @Column(name = "description")
    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // убирает зацикливание в JSON
    @OneToMany(mappedBy = "category", targetEntity = Item.class)
    private List<Item> items;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + categoryName + '\'' +
                '}';
    }
}