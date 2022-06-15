package dev.lkenselaar.social.network.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 14-6-2022
 */
@Validated
@Data
public class CreateUserResponseDTO {
    @JsonProperty("name")
    @Schema(example = "John Doe", required = true)
    private String name;

    @JsonProperty("username")
    @Schema(example = "john", required = true)
    private String username;
}
