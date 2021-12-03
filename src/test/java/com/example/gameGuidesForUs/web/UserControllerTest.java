package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private final String TEST_USERNAME = "mockTest";
    private final String TEST_FIRST_NAME = "Mocky";
    private final String TEST_LAST_NAME = "Test";
    private final String TEST_EMAIL = "mocktest@test.com";
    private final String TEST_PASSWORD = "12345";
    private final String TEST_CONFIRM_PASSWORD = "12345";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Mockito.mock(UserRepository.class);
    }

    @Test
    void testOpenLogForm() throws Exception {
        mockMvc.perform(get("/users/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc
                .perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }


    @Test
    void testRegisterUser() throws Exception {

        long initialUserCount = userRepository.count();
        mockMvc.perform(post("/users/register")
                .param("firstName", TEST_FIRST_NAME)
                .param("lastName", TEST_LAST_NAME)
                .param("username", TEST_USERNAME).accept(TEST_USERNAME)
                .param("password", TEST_PASSWORD)
                .param("confirmPassword", TEST_CONFIRM_PASSWORD)
                .param("email", "mocktest@test.com").accept(TEST_EMAIL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is3xxRedirection());

        Assertions.assertEquals(initialUserCount + 1, userRepository.count());

        Optional<User> newUser = userRepository.findByUsername(TEST_USERNAME);
        newUser.get().setRegisteredOn(Instant.now());
        Assertions.assertTrue(newUser.isPresent());
        Assertions.assertEquals(TEST_USERNAME, newUser.get().getUsername());
        Assertions.assertEquals(TEST_FIRST_NAME, newUser.get().getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, newUser.get().getLastName());
        Assertions.assertEquals(TEST_EMAIL, newUser.get().getEmail());

        userRepository.deleteById(newUser.get().getId());
    }


}

