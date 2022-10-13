package dev.lkenselaar.social.network.controller;

import dev.lkenselaar.social.network.model.DTO.Post.CreatePostRequestDTO;
import dev.lkenselaar.social.network.model.DTO.Post.CreatePostResponseDTO;
import dev.lkenselaar.social.network.model.DTO.Post.PostResponseDTO;
import dev.lkenselaar.social.network.model.Post;
import dev.lkenselaar.social.network.model.User;
import dev.lkenselaar.social.network.service.PostService;
import dev.lkenselaar.social.network.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 12/10/2022
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/posts")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(
        summary = "Get posts",
        description = "Get all posts",
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {"Post controller"}
    )
    public ResponseEntity<?> getPosts() {
        try {
            List<Post> posts = postService.getPosts();

            // Convert Entities to list of DTOs
            List<PostResponseDTO> response = posts.stream().map(post -> modelMapper.map(post, PostResponseDTO.class)).collect(Collectors.toList());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(
        summary = "Get post",
        description = "Get a specific post by id",
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {"Post controller"}
    )
    public ResponseEntity<?> getPost(@PathVariable int id) {
        try {
            Post post = postService.getPostById(id);

            // Convert Entity to DTO
            PostResponseDTO response = modelMapper.map(post, PostResponseDTO.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/posts")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Operation(
        summary = "Create post",
        description = "Create a new post",
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {"Post controller"}
    )
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequestDTO body) {
        try {
            // Convert DTO to Entity
            Post post = modelMapper.map(body, Post.class);

            // Set the owner
            User user = userService.findByUsername(request.getUserPrincipal().getName());
            post.setUser(user);

            Post createdPost = postService.add(post);

            CreatePostResponseDTO response = modelMapper.map(createdPost, CreatePostResponseDTO.class);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
