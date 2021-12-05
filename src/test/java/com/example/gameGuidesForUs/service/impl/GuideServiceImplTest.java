package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GuideServiceImplTest {

    private GuideServiceImpl serviceToTest;
    private Guide guideToTest;

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private GuideRepository mockGuideRepository;
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private GameRepository mockGameRepository;

    @BeforeEach
    void init() {
        serviceToTest = new GuideServiceImpl(mockModelMapper,mockGuideRepository,
                mockUserRepository,mockGameRepository);

        guideToTest = new Guide();
        guideToTest.setGuideTitle("TestGuide")
                .setCreatedOn(Instant.now())
                .setGuideCreatedBy(new User())
                .setGameId(new Game())
                .setDescription("TestGuideDescription......")
                .setComments(new ArrayList<>());

    }

    @Test
    void testFindAllGuidesForGivenGame() {
        Guide guideToTest = this.guideToTest;
        serviceToTest.findAllGuidesForGivenGame(guideToTest.getId());
    }

    @Test
    void testIfGuideTitleIsFree() {
        Guide guideToTest = this.guideToTest;
        Assertions.assertTrue(mockGameRepository
                .findByGameTitle(guideToTest.getGuideTitle()).isEmpty());
    }


    @Test
    void testFindingGuideById() {
        Guide guideToTest = this.guideToTest;
        ObjectNotFound throwsObjNF = Assertions.assertThrows(ObjectNotFound.class, () -> {
            serviceToTest.findGuideById(guideToTest.getId());
            Mockito.when(mockGuideRepository.findById(guideToTest.getId()))
                    .thenReturn(Optional.of(guideToTest));
        });
    }

    @Test
    void testDeletionOfGuide() {

    }
}