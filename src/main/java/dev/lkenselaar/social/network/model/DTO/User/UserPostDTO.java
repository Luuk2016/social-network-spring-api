package dev.lkenselaar.social.network.model.DTO.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 15/06/2022
 */
@Data
public class UserPostDTO {
    @JsonProperty("id")
    @Schema(example = "1", required = true)
    private Long id;

    @JsonProperty("name")
    @Schema(example = "John Doe", required = true)
    private String name;
}
