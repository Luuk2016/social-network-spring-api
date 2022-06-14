package dev.lkenselaar.socialnetwork.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-6-2022
 */
@Validated
@Data
public class AuthenticateResponseDTO {
    @JsonProperty("accessToken")
    @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851", required = true)
    private String accessToken;
}