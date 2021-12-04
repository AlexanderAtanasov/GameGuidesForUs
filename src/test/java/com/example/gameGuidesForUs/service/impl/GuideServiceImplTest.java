package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class GuideServiceImplTest {

    private Guide testGuide;
    private User testUser;
    private Game testGame;
    private UserRoleEntity adminRole, userRole;
    private GuideServiceImpl serviceToTest;

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private GameRepository mockGameRepository;
    @Mock
    private CommentRepository mockCommentRepository;

    @Mock
    private GuideRepository mockGuideRepository;

    @BeforeEach
    void setUp() {
      serviceToTest = new GuideServiceImpl(mockModelMapper,mockGuideRepository,mockUserRepository,mockGameRepository,mockCommentRepository);
    }


    @Test
    void findAllGuidesForGivenGame() {
    }

    @Test
    void isGuideTitleFree() {

        Assertions.assertTrue(mockGuideRepository.findByGuideTitle("does-not-exist").isEmpty());

    }

    @Test
    void addGuide() {

        GuideAddServiceModel guideAddServiceModel = new GuideAddServiceModel();
        guideAddServiceModel.setGuideTitle("TestGuideTitle")
                .setComments(new ArrayList<>())
                .setGuideCreatedBy("TestUser")
                .setGameId(1L)
                .setCreatedOn(Instant.now())
                .setDescription("TestMockiTestTestMockiTestTestMockiTest");

        Mockito.when(mockGuideRepository.save(mockModelMapper.map(guideAddServiceModel,Guide.class)))
                .thenReturn(mockGuideRepository.findByGuideTitle("TestGuideTitle").get());

//        this.mockGuideRepository.save(testGuide);
//        Assertions.assertEquals(1,this.mockGuideRepository.count());


    }

    @Test
    void findGuideById() {

    }

    @Test
    void deleteGuide() {
       var sut = new GuideServiceImpl(mockModelMapper,mockGuideRepository,mockUserRepository,
               mockGameRepository,mockCommentRepository);


    }

    @Test
    void getGameOfTheGuide() {
    }
}