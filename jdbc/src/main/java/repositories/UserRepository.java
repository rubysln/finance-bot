package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByChatId(Long chatId);

    void deleteUserByChatId(Long chatId);
}
