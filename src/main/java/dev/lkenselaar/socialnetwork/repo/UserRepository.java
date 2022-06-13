package dev.lkenselaar.socialnetwork.repo;

import dev.lkenselaar.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
