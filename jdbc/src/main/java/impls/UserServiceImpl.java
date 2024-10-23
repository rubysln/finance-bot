package impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;
import services.UserService;
import user.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
    public void postUser(User object) {
        userRepository.save(object);
    }

    @Override
    public void deleteUserByChatId(Long chatId) {
        userRepository.deleteById(chatId);
    }
}
