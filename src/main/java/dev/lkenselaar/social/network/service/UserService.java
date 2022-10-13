package dev.lkenselaar.social.network.service;

import dev.lkenselaar.social.network.security.JwtTokenProvider;
import dev.lkenselaar.social.network.model.User;
import dev.lkenselaar.social.network.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = userRepository.findByUsername(username);
            return jwtTokenProvider.createToken(user.getId(), username, user.getRoles());
        } catch (AuthenticationException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username/password invalid");
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById((long) id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String name) {
        if (userRepository.findByUsername(name) == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username not found");
        }
        return userRepository.findByUsername(name);
    }
}