package dev.lkenselaar.social.network.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lkenselaar.social.network.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 15/06/2022
 */
@Data
@DynamicUpdate
public class UpdateUserRequestDTO {
    @JsonProperty("name")
    @Schema(example = "John Doe", required = true)
    private String name;

    @JsonProperty("username")
    @Schema(example = "john", required = true)
    private String username;

    @JsonProperty("password")
    @Schema(example = "password", required = true)
    private String password;

    @JsonProperty("roles")
    @Schema(example = "ROLE_USER", required = true)
    private List<Role> roles;
}
