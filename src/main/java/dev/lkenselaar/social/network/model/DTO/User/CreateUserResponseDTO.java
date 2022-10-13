package dev.lkenselaar.social.network.model.DTO.User;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;

    @JsonProperty("username")
    private String username;
}
