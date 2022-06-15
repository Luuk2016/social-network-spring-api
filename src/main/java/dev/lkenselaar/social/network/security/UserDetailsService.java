package dev.lkenselaar.social.network.security;

import dev.lkenselaar.social.network.model.User;
import dev.lkenselaar.social.network.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User " + s + " not found");
        }

        return org.springframework.security.core.userdetails.User.withUsername(s).password(user.getPassword()).authorities(user.getRoles()).accountExpired(false).accountLocked(false).credentialsExpired(false).disabled(false).build();
    }
}