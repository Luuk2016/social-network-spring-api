package dev.lkenselaar.socialnetwork.configuration;

import dev.lkenselaar.socialnetwork.model.Role;
import dev.lkenselaar.socialnetwork.model.User;
import dev.lkenselaar.socialnetwork.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Component
@AllArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.save(new User(null, "John Doe", "john", "password", List.of(Role.ROLE_USER)));
        userService.save(new User(null, "Jane Roe", "jane", "password", List.of(Role.ROLE_USER, Role.ROLE_ADMIN)));
    }
}
