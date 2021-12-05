package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameAddServiceModelTest {

    private String gameTitle;
    private GenreEnum genre;
    private String gameDescription;
    private String gameScreenshotUrl;
    private LocalDate releasedOn;

    private GameAddServiceModel testModel;

    @BeforeEach
    void init() {
        testModel = new GameAddServiceModel();
        gameTitle = "GameTitle";
        genre = GenreEnum.FPS;
        gameDescription = "New Game Description.........";
        gameScreenshotUrl = "someUrlForScreenshot";
        releasedOn = LocalDate.now();
    }


    @Test
    void testAll() {
        GameAddServiceModel testModel = this.testModel;
        testModel.setGameTitle(gameTitle);
        testModel.setGameScreenshotUrl(gameScreenshotUrl);
        testModel.setGenre(genre);
        testModel.setGameDescription(gameDescription);
        testModel.setReleasedOn(releasedOn);


        Assertions.assertEquals(gameTitle,testModel.getGameTitle());
        Assertions.assertEquals(gameScreenshotUrl,testModel.getGameScreenshotUrl());
        Assertions.assertEquals(genre,testModel.getGenre());
        Assertions.assertEquals(gameDescription,testModel.getGameDescription());
        Assertions.assertEquals(releasedOn,testModel.getReleasedOn());
    }

}