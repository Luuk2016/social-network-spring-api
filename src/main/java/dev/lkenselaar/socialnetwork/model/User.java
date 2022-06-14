package dev.lkenselaar.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name can't be blank")
    private String name;

    @JsonProperty("username")
    @NotBlank(message = "Username can't be blank")
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password can't be blank")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;
}