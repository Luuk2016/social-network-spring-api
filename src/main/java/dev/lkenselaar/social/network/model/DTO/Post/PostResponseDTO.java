package dev.lkenselaar.social.network.model.DTO.Post;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lkenselaar.social.network.model.DTO.User.UserPostDTO;
import lombok.Data;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13/10/2022
 */
@Data
public class PostResponseDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("datetime")
    private String dateTime;

    @JsonProperty("user")
    private UserPostDTO user;

    @JsonProperty("content")
    private String content;
}
