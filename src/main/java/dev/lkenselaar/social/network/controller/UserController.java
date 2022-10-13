package dev.lkenselaar.social.network.controller;

import dev.lkenselaar.social.network.model.DTO.User.CreateUserRequestDTO;
import dev.lkenselaar.social.network.model.DTO.User.CreateUserResponseDTO;
import dev.lkenselaar.social.network.model.DTO.User.UserDTO;
import dev.lkenselaar.social.network.service.UserService;
import dev.lkenselaar.social.network.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 14/06/2022
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/users")
    @Operation(
        summary = "Create user",
        description = "Create a new user",
        tags = {"User controller"}
    )
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDTO body) {
        try {
            // Convert DTO to Entity
            User user = modelMapper.map(body, User.class);

            // Add the user
            User result = userService.add(user);

            // Convert Entity to DTO
            CreateUserResponseDTO response = modelMapper.map(result, CreateUserResponseDTO.class);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(
        summary = "Get users (admin only)",
        description = "Get all registered users",
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {"User controller"}
    )
    public ResponseEntity<?> getUsers() {
        try {
            // Get the users
            List<User> users = userService.getUsers();

            // Convert Entities to list of DTOs
            List<UserDTO> response = users.stream().map(post -> modelMapper.map(post, UserDTO.class)).collect(Collectors.toList());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(
        summary = "Get user (admin only)",
        description = "Get a specific user by id",
        security = @SecurityRequirement(name = "bearerAuth"),
        tags = {"User controller"}
    )
    public ResponseEntity<?> getUser(@PathVariable int id) {
        try {
            // Get the user
            User user = userService.getUserById(id);

            // Convert Entity to DTO
            UserDTO response = modelMapper.map(user, UserDTO.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
