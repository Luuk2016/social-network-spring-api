package dev.lkenselaar.socialnetwork.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "john", required = true)
    private String username;

    @JsonProperty("password")
    @Schema(example = "password", required = true)
    private String password;
}
