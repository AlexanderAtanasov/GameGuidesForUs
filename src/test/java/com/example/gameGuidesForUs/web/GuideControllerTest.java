package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.ScreenshotService;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@WithUserDetails
class GuideControllerTest {

    @Autowired
    private GuideService mockGuideService;
    @Autowired
    private CommentService mockCommentService;
    @Autowired
    private ModelMapper mockModelMapper;
    @Autowired
    private ScreenshotService mockScreenshotService;
    @Autowired
    private GuideRepository mockGuideRepo;

    @Autowired
    private MockMvc mockMvc;

    private Guide testGuide;
    private Game testGame;
    private OnlineUser testOnlineUser;

    @BeforeEach
    void init() {
        Mockito.mock(GuideService.class);
        Mockito.mock(CommentService.class);
        Mockito.mock(ModelMapper.class);
        Mockito.mock(ScreenshotService.class);
        Mockito.mock(GuideRepository.class);
        testGuide = new Guide();
        testGuide.setGuideCreatedBy(new User())
                .setGuideTitle("TestTitleGuide")
                .setComments(new ArrayList<>())
                .setDescription("SomeGuideDescription..........")
                .setGameId(testGame)
                .setCreatedOn(Instant.now())
                .setId(1L);

        testOnlineUser = new OnlineUser("username", "password", new ArrayList<>());
        testGame = new Game();
        testGame.setGameScreenshotUrl("ScreenshotUrl..........")
                .setReleasedOn(LocalDate.now())
                .setGuides(new ArrayList<>())
                .setGenre(GenreEnum.FPS)
                .setGameTitle("GameTitle.....")
                .setGameDescription("GameDescription.........")
                .setId(1L);

    }

    @Test
    void openAddGuideForm() throws Exception {
        Game testGame = this.testGame;
        var gameId = testGame.getId();

        mockMvc.perform(get("/games/guides/" + gameId + "/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("guide-add"));
    }

    @Test
    void addGuideSuccess() throws Exception {
        Guide testGuide = this.testGuide;

        OnlineUser testOnlineUser = this.testOnlineUser;
        var gameId = testGame.getId();
        mockMvc.perform(post("/games/guides/" + gameId + "/add")
                .param("guideTitle", "TestTitleGuide")
                .param("description", "SomeGuideDescription..........")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().is3xxRedirection());

    }

    @Test
    void testViewGuide() throws Exception {
        long guideId = 1L;

        mockMvc.perform(get("/guides/" + guideId + "/view"))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("currentGuide", mockGuideService.findGuideById(guideId)))
                .andExpect(MockMvcResultMatchers.model()
                        .attribute("allCommentsForTheGuide", mockCommentService.findByGuideId(guideId)))
                .andExpect(status().isOk());
    }
}