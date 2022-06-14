package dev.lkenselaar.socialnetwork.controller;

import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateRequestDTO;
import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateResponseDTO;
import dev.lkenselaar.socialnetwork.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 14/06/2022
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate", description = "Receive a token to authenticate with the API", tags = {"Auth"})
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestDTO body) {
        try {
            String accessToken = userService.authenticate(body.getUsername(), body.getPassword());

            AuthenticateResponseDTO authenticateResponse = new AuthenticateResponseDTO();
            authenticateResponse.setAccessToken(accessToken);

            return new ResponseEntity<>(authenticateResponse, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
