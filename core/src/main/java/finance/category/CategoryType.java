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

    public static final String INCOMES_SYS_NAME = "income";
    public static final String EXPENSE_SYS_NAME = "expense";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sysName;

    public CategoryType(String sys_name){
        this.sysName = sys_name;
    }
}
