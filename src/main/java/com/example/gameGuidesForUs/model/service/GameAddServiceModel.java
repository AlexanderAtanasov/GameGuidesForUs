package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;

import java.time.LocalDate;

public class GameAddServiceModel {

    private String gameTitle;
    private GenreEnum genre;
    private String gameDescription;
    private String gameScreenshotUrl;
    private LocalDate releasedOn;

    public GameAddServiceModel() {
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public GameAddServiceModel setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public GameAddServiceModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public GameAddServiceModel setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
        return this;
    }

    public String getGameScreenshotUrl() {
        return gameScreenshotUrl;
    }

    public GameAddServiceModel setGameScreenshotUrl(String gameScreenshotUrl) {
        this.gameScreenshotUrl = gameScreenshotUrl;
        return this;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public GameAddServiceModel setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
        return this;
    }

}
