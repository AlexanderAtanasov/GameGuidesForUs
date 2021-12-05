package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    private PasswordEncoder passwordEncoder;


    private User testUser;
    private UserRoleEntity adminRole, userRole;

    @BeforeEach
    void setUp() {

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

//        testUser = new User();
//        testUser.setUsername(TEST_USERNAME)
//                .setRoles(Set.of(userRole))
//                .setRegisteredOn(Instant.now())
//                .setFirstName(TEST_LAST_NAME)
//                .setLastName(TEST_LAST_NAME)
//                .setEmail(TEST_EMAIL)
//                .setGuides(new ArrayList<>())
//                .setAllUserComments(new ArrayList<>()).setId(1L);
//        testUser.setPassword(passwordEncoder.encode(TEST_PASSWORD));
//


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
    void testRegisterUserSuccess() throws Exception {


        mockMvc.perform(post("/users/register")
                .param("username", TEST_USERNAME)
                .param("password", TEST_PASSWORD)
                .param("confirmPassword", TEST_CONFIRM_PASSWORD)
                .param("firstName", TEST_FIRST_NAME)
                .param("lastName", TEST_LAST_NAME)
                .param("email", TEST_EMAIL)
                .with(csrf())).andExpect(status().is3xxRedirection());

        User newUser = userRepository.findByUsername(TEST_USERNAME).get();

        Assertions.assertEquals(newUser.getUsername(), TEST_USERNAME);
        Assertions.assertEquals(newUser.getEmail(), TEST_EMAIL);

    }

    @Test
    void testRegisterDifferentPasswords() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("username", TEST_USERNAME)
                        .param("password", TEST_PASSWORD)
                        .param("confirmPassword", "TESTPASSWORD")
                        .param("firstName", TEST_FIRST_NAME)
                        .param("lastName", TEST_LAST_NAME)
                        .param("email", TEST_EMAIL)
                        .with(csrf())).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:register"));

    }


    @Test
    void testLoginUser() throws Exception {
        passwordEncoder = new Pbkdf2PasswordEncoder();
        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        User newUser = new User();
        newUser.setUsername(TEST_USERNAME)
                .setFirstName(TEST_LAST_NAME)
                .setLastName(TEST_LAST_NAME)
                .setEmail(TEST_EMAIL)
                .setGuides(new ArrayList<>())
                .setRegisteredOn(Instant.now())
//                .setRoles(Set.of(adminRole, userRole))
                .setAllUserComments(new ArrayList<>())
                .setPassword(passwordEncoder.encode(TEST_PASSWORD));




        Assertions.assertEquals(newUser.getUsername(), TEST_USERNAME);
        Assertions.assertEquals(newUser.getEmail(), TEST_EMAIL);

        mockMvc.perform(post("/users/login").//
                param("username", TEST_USERNAME).//
                param("password", TEST_PASSWORD).//
                with(csrf())).andExpect(status().isOk());
    }


}

