package finance;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.User;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "savings", schema = "finance")
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "savings", cascade = CascadeType.ALL)
    private List<Goal> goals;
}
