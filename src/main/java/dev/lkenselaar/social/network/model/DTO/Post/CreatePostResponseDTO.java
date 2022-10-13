package dev.lkenselaar.social.network.model.DTO.Post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-10-2022
 */
@Validated
@Data
public class CreatePostResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("datetime")
    private OffsetDateTime datetime;

    @JsonProperty("content")
    private String content;
}
