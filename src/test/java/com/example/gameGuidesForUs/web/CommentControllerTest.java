package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = {"USER", "ADMIN"})
class CommentControllerTest {

    @Mock
    private CommentService mockCommentService;
    @Mock
    private UserService mockUserService;

    @Autowired
    private MockMvc mockMvc;


}