package user;

import finance.Expense;
import finance.Income;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "data")
public class User {
    @Id
    private Long chatId;

    private String firstName;
    private String lastName;
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Income> incomes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Expense> expenses;

    public User(Long chatId){
        this.chatId = chatId;
    }
}


