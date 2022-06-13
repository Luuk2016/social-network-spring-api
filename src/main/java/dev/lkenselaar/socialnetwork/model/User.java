package dev.lkenselaar.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
}