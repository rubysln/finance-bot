package finance;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "goal", schema = "finance")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal targetAmount;
    private Date targetDate;

    @ManyToOne
    @JoinColumn(name = "savings_id", nullable = false)
    private Savings savings;
}
