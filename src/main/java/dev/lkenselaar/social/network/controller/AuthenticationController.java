package dev.lkenselaar.social.network.controller;

import dev.lkenselaar.social.network.model.DTO.AuthenticateRequestDTO;
import dev.lkenselaar.social.network.model.DTO.AuthenticateResponseDTO;
import dev.lkenselaar.social.network.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 15/06/2022
 */
@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate", description = "Receive a token to authenticate with the API", tags = {"Auth"})
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticateRequestDTO body) {
        try {
            String accessToken = userService.authenticate(body.getUsername(), body.getPassword());

            AuthenticateResponseDTO response = new AuthenticateResponseDTO();
            response.setAccessToken(accessToken);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
