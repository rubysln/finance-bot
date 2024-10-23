package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByChatId(Long chatId);

    void deleteUserByChatId(Long chatId);
}
