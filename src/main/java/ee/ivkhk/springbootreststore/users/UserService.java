package ee.ivkhk.springbootreststore.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEcoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEcoder) {
        this.userRepository = userRepository;
        this.passwordEcoder = passwordEcoder;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void add(User user) {
        user.setPassword(passwordEcoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(Long userId) {
    }

    public void update(User user) {
    }
}
