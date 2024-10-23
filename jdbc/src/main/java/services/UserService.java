package services;

import org.springframework.stereotype.Service;
import user.User;

import java.util.List;

@Service
public interface UserService {
    /*
        User
     */

    List<User> getUsers();

    long getCountUsers();

    User getUserByChatId(Long chatId);

    User postUser(User object);

    void deleteUserByChatId(Long chatId);
}
