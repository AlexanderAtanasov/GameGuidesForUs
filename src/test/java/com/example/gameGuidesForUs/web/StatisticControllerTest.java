package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
class StatisticControllerTest {

    @Mock
    private StatisticService statisticService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetStatistics() throws Exception {
        mockMvc.perform(get("/statistics")).andExpect(status().isOk())
                .andExpect(view().name("statistics"));
    }
}