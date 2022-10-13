package dev.lkenselaar.social.network.configuration;

import dev.lkenselaar.social.network.model.Post;
import dev.lkenselaar.social.network.model.Role;
import dev.lkenselaar.social.network.model.User;
import dev.lkenselaar.social.network.service.PostService;
import dev.lkenselaar.social.network.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Component
@AllArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final UserService userService;
    private final PostService postService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create two new users
        User john = new User(null, "John Doe", "john", "password", List.of(Role.ROLE_USER), List.of());
        User jane = new User(null, "Jane Roe", "jane", "password", List.of(Role.ROLE_USER, Role.ROLE_ADMIN), List.of());

        // Add the users
        userService.add(john);
        userService.add(jane);

        // Create two new posts
        Post post1 = new Post(null, OffsetDateTime.now(), john, "First post!");
        Post post2 = new Post(null, OffsetDateTime.now(), john, "Second post!");
        Post post3 = new Post(null, OffsetDateTime.now(), john, "Third post!");

        // Add the posts
        postService.add(post1);
        postService.add(post2);
        postService.add(post3);
    }
}
