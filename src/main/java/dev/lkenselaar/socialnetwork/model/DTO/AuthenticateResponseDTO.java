package dev.lkenselaar.socialnetwork.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-6-2022
 */
@Validated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResponseDTO {
    @JsonProperty("accessToken")
    private String accessToken;
}