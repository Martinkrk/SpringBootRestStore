package ee.ivkhk.springbootreststore.users;

import ee.ivkhk.springbootreststore.products.Product;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("list")
    public List<User> listUsers() {
        return userService.list();
    }

    @PostMapping("item")
    public void addUser(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping("item/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @PutMapping("item")
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }
}
