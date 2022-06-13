package dev.lkenselaar.socialnetwork.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Validated
@Data
public class AuthenticateRequestDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
