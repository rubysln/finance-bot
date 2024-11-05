package repositories;

import finance.Income;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    @Query("SELECT i FROM Income i WHERE i.user.chatId = :chatId ORDER BY i.incomeDate DESC")
    List<Income> findIncomesByUserChatId(Long chatId);
}