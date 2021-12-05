package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.model.view.GameViewModel;
import com.example.gameGuidesForUs.model.view.HomeViewModel;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContextException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    private GameServiceImpl serviceToTest;
    private Game testGame;

    @Mock
    private GameRepository mockedGameRepository;
    @Mock
    private ModelMapper mockedModelMapper;

    @BeforeEach
    void init() {
        serviceToTest = new GameServiceImpl(mockedGameRepository, mockedModelMapper);
        testGame = new Game();
        testGame.setGameTitle("TestGame")
                .setGuides(new ArrayList<>())
                .setGenre(GenreEnum.MOBA)
                .setGameDescription("TestGameDescription...")
                .setReleasedOn(LocalDate.now());

    }

    @Test
    void testDeleteGame() {

        Game testGame = this.testGame;

        serviceToTest.deleteGame(testGame.getId());

        Mockito.verify(mockedGameRepository, Mockito.times(1)).deleteById(testGame.getId());

    }

    @Test
    void testIsGameTitleFree() {

        Game testGame = this.testGame;

        serviceToTest.isGameTitleFree(testGame.getGameTitle());
        Mockito.verify(mockedGameRepository, Mockito.times(1))
                .findByGameTitle(testGame.getGameTitle());

    }

    @Test
    void testAddGame() {
        Game testGame = this.testGame;
        GameAddServiceModel gameAddServiceModel = mockedModelMapper.map(testGame, GameAddServiceModel.class);
        serviceToTest.addGame(gameAddServiceModel);

        Mockito.verify(mockedGameRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void testGetGameCount() {
        serviceToTest.getTotalGameCount();
        Mockito.verify(mockedGameRepository, Mockito.times(1))
                .count();
    }

    @Test
    void testGetAllGamesSortedByReleaseDate() {
        List<GameViewModel> testGameViewModelList = new ArrayList<>();
        serviceToTest.findAllGamesSortByReleaseDateDesc();
    }

    @Test
    void testFindScreenshotId() {
        Game testGame = this.testGame;

        Mockito.when(mockedGameRepository.findById(testGame.getId()))
                .thenReturn(Optional.of(testGame));
        String testScreenshotUrl = "Some Url Test.anotheru/rlf/tests.com";
        testGame.setGameScreenshotUrl(testScreenshotUrl);
        serviceToTest.findGameScreenshotId(testGame.getId());

    }

    @Test
    void testFindGameInformationById() {
        Game testGame = this.testGame;
        ObjectNotFound throwsObjNF = Assertions.assertThrows(ObjectNotFound.class, () -> {
            serviceToTest.findGameInformationById(testGame.getId());
        });
    }


    @Test
    void testGetAllGamesForRandomPresentation() {
        Game testGame = this.testGame;
        HomeViewModel testHomeView = new HomeViewModel();
        List<Game> testGameList = new ArrayList<>();
        List<HomeViewModel> testList = new ArrayList<>();
        testList.add(testHomeView);



    }
}