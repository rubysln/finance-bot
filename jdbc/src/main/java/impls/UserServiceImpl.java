package impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.UserRepository;
import services.UserService;
import user.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public long getCountUsers() {
        return userRepository.count();
    }

    @Override
    public User getUserByChatId(Long chatId) {
        return userRepository.getUserByChatId(chatId);
    }

    @Override
    public User postUser(User object) {
        return userRepository.save(object);
    }

    @Override
    public void deleteUserByChatId(Long chatId) {
        userRepository.deleteById(chatId);
    }
}
