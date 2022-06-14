package dev.lkenselaar.socialnetwork.controller;

import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateRequestDTO;
import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateResponseDTO;
import dev.lkenselaar.socialnetwork.model.DTO.CreateUserRequestDTO;
import dev.lkenselaar.socialnetwork.model.DTO.CreateUserResponseDTO;
import dev.lkenselaar.socialnetwork.model.Role;
import dev.lkenselaar.socialnetwork.model.User;
import dev.lkenselaar.socialnetwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 14/06/2022
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/authenticate")
    @Operation(summary = "Authenticate", description = "Receive a token to authenticate with the API", tags = {"Auth"})
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticateRequestDTO body) {
        try {
            String accessToken = userService.authenticate(body.getUsername(), body.getPassword());

            AuthenticateResponseDTO authenticateResponse = new AuthenticateResponseDTO();
            authenticateResponse.setAccessToken(accessToken);

            return new ResponseEntity<>(authenticateResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/users")
    @Operation(summary = "Create user", description = "Create a new user", tags = {"User"})
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestDTO body) {
        try {
            User user = new User();
            user.setName(body.getName());
            user.setUsername(body.getUsername());
            user.setPassword(body.getPassword());
            user.setRoles(List.of(Role.ROLE_USER));

            User result = userService.save(user);

            CreateUserResponseDTO response = new CreateUserResponseDTO();
            response.setName(result.getName());
            response.setUsername(result.getUsername());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get users", description = "Get all registered users", tags = {"Admin"})
    public ResponseEntity<?> getUsers() {
        try {
            List<User> users = userService.getUsers();

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Get user", description = "Get a specific user by id", tags = {"Admin"})
    public ResponseEntity<?> getUsers(@PathVariable int id) {
        try {
            User user = userService.getUserById(id);

            return new ResponseEntity<>(user, HttpStatus.OK);
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
