package dev.lkenselaar.socialnetwork.controller;

import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateRequestDTO;
import dev.lkenselaar.socialnetwork.model.DTO.AuthenticateResponseDTO;
import dev.lkenselaar.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@RestController
public class AuthenticateController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
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
