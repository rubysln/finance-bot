package finance.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category_type", schema = "finance")
public class CategoryType {

    private static String INCOMES_SYS_NAME = "income";
    private static String EXPENSE_SYS_NAME = "expense";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
