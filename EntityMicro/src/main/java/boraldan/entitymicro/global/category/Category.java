package boraldan.entitymicro.global.category;

import boraldan.entitymicro.shop.entity.item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
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


    @JsonBackReference
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