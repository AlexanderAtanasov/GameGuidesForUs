package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.repository.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        serviceToTest = new GameServiceImpl(mockedGameRepository,mockedModelMapper);
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

        Mockito.verify(mockedGameRepository,Mockito.times(1)).deleteById(testGame.getId());

    }

    @Test
    void testIsGameTitleFree() {

        Game testGame = this.testGame;

        serviceToTest.isGameTitleFree(testGame.getGameTitle());
        Mockito.verify(mockedGameRepository,Mockito.times(1))
                .findByGameTitle(testGame.getGameTitle());

    }

    @Test
    void testAddGame() {
        Game testGame = this.testGame;
        GameAddServiceModel gameAddServiceModel = mockedModelMapper.map(testGame,GameAddServiceModel.class);
      serviceToTest.addGame(gameAddServiceModel);

        Mockito.verify(mockedGameRepository, Mockito.times(1)).save(Mockito.any());

    }


}