package dev.lkenselaar.social.network.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lkenselaar.social.network.model.DTO.Post.CreatePostRequestDTO;
import dev.lkenselaar.social.network.model.DTO.User.CreateUserRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-10-2022
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "john", roles = "USER")
    public void getPostsShouldReturnList() throws Exception {
        mockMvc.perform(get("/posts").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].content", is("First post!")))
                .andExpect(jsonPath("$[0].user.name", is("John Doe")))
                .andExpect(jsonPath("$[1].content", is("Second post!")))
                .andExpect(jsonPath("$[1].user.name", is("John Doe")));
    }

    @Test
    @WithMockUser(username = "john", roles = "USER")
    public void getSpecificPostShouldReturnPost() throws Exception {
        mockMvc.perform(get("/posts/3").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("First post!")))
                .andExpect(jsonPath("$.user.name", is("John Doe")));
    }

    @Test
    @WithMockUser(username = "john", roles = "USER")
    public void createPostSuccessfullyShouldReturnPost() throws Exception {
        CreatePostRequestDTO post = new CreatePostRequestDTO();
        post.setContent("Content of the post");

        this.mockMvc.perform(post("/posts").content(asJsonString(post)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is("Content of the post")));
    }

    @Test
    public void createPostWithoutAccountShouldGiveForbidden() throws Exception {
        CreatePostRequestDTO post = new CreatePostRequestDTO();
        post.setContent("Content of the post");

        this.mockMvc.perform(post("/posts").content(asJsonString(post)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
