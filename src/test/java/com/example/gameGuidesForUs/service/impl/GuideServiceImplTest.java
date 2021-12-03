package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
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

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private GameRepository mockGameRepository;
    @Mock
    private CommentRepository mockCommentRepository;

    @MockBean
    private GuideRepository mockGuideRepository;

    @BeforeEach
    void init() {

        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        testUser = new User();
        testUser.setUsername("TestMock")
                .setFirstName("Mocki")
                .setLastName("Mockev")
                .setPassword("testmock")
                .setEmail("testmock@test.com")
                .setRegisteredOn(Instant.now())
                .setGuides(new ArrayList<>())
                .setRoles(Set.of(adminRole, userRole));

        testGame = new Game();
        testGame.setGameDescription("TestGameForMocking")
                .setGenre(GenreEnum.MOBA)
                .setGameTitle("MockitoTesting")
                .setGuides(new ArrayList<>())
                .setReleasedOn(LocalDate.now());

        testGuide = new Guide();
        testGuide.setGameId(testGame)
                .setGuideCreatedBy(testUser)
                .setCreatedOn(Instant.now())
                .setDescription("TestMockiTestTestMockiTestTestMockiTest")
                .setGuideTitle("TestGuideTitle")
                .setComments(new ArrayList<>())
                .setId(1L);
        System.out.printf("");
        mockGuideRepository.save(testGuide);
        System.out.println();

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


        long countBefore = mockGuideRepository.count();
        mockGuideRepository.save(testGuide);
        Assertions.assertEquals(countBefore+1,mockGuideRepository.count());


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