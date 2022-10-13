package dev.lkenselaar.social.network.model.DTO.Post;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 13-10-2022
 */
@Validated
@Data
public class CreatePostRequestDTO {
    @Schema(example = "Hello World!", required = true)
    @NotBlank(message = "Content can't be blank")
    private String content;
}
