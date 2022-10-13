package dev.lkenselaar.social.network.repo;

import dev.lkenselaar.social.network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-10-2022
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
