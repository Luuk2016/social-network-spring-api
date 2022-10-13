package dev.lkenselaar.social.network.model.DTO.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String accessToken;

    @JsonProperty("tokenType")
    private String tokenType = "Bearer";
}