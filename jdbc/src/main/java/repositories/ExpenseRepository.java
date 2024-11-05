package repositories;

import finance.Expense;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.user.chatId = :chatId ORDER BY e.expenseDate DESC")
    List<Expense> findExpenseByUserChatId(Long chatId);
}