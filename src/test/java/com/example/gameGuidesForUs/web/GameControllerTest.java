package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.view.GameViewModel;
import com.example.gameGuidesForUs.model.view.GuideListViewModel;
import com.example.gameGuidesForUs.service.GameService;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
class GameControllerTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAddGameForm() throws Exception {
        mockMvc.perform(get("/games/add")).andExpect(status().isOk())
                .andExpect(view().name("game-add"));
    }

    @Test
    void getAllGamesView() throws Exception {
        mockMvc.perform(get("/games/all")).andExpect(status().isOk())
                .andExpect(view().name("games"));
    }

}