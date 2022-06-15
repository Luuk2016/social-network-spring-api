package dev.lkenselaar.social.network.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-6-2022
 */
@Validated
@Data
public class AuthenticateRequestDTO {
    @Schema(example = "john", required = true)
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Schema(example = "password", required = true)
    @NotBlank(message = "Password can't be blank")
    private String password;
}
