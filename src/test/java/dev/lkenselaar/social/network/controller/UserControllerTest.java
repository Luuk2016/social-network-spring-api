package dev.lkenselaar.social.network.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lkenselaar.social.network.model.DTO.User.CreateUserRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Luuk Kenselaar (https://lkenselaar.dev)
 * @since 11-10-2022
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "jane", roles = "ADMIN")
    public void createUserSuccessfullyShouldGiveIsCreated() throws Exception {
        CreateUserRequestDTO user = new CreateUserRequestDTO();
        user.setName("Peter Griffin");
        user.setUsername("peter");
        user.setPassword("test");

        this.mockMvc.perform(post("/users").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Peter Griffin")))
                .andExpect(jsonPath("$.username", is("peter")));
    }

    @Test
    @WithMockUser(username = "jane", roles = "ADMIN")
    public void createUserWithoutNameShouldGiveError() throws Exception {
        CreateUserRequestDTO user = new CreateUserRequestDTO();
        user.setUsername("peter");
        user.setPassword("test");

        this.mockMvc.perform(post("/users").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Name can't be blank")));
    }

    @Test
    @WithMockUser(username = "jane", roles = "ADMIN")
    public void createUserWithoutUsernameShouldGiveError() throws Exception {
        CreateUserRequestDTO user = new CreateUserRequestDTO();
        user.setName("Peter Griffin");
        user.setPassword("test");

        this.mockMvc.perform(post("/users").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("Username can't be blank")));
    }

    @Test
    @WithMockUser(username = "jane", roles = "ADMIN")
    public void createUserWithoutPasswordShouldGiveError() throws Exception {
        CreateUserRequestDTO user = new CreateUserRequestDTO();
        user.setName("Peter Griffin");
        user.setUsername("peter");

        this.mockMvc.perform(post("/users").content(asJsonString(user)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.password", is("Password can't be blank")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
