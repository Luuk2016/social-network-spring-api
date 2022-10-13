package dev.lkenselaar.social.network.service;

import dev.lkenselaar.social.network.model.Post;
import dev.lkenselaar.social.network.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-10-2022
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        Optional<Post> post = postRepository.findById((long) id);

        if (post.isPresent()) {
            return post.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
    }

    public Post add(Post post) {
        post.setDatetime(OffsetDateTime.now());

        return postRepository.save(post);
    }
}