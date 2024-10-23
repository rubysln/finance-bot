package finance.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category", schema = "finance")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_type_id")
    private CategoryType type;

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }
}
