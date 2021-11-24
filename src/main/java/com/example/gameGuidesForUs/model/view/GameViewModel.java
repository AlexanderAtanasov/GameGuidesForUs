package com.example.gameGuidesForUs.model.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GameViewModel {

    private Long id;
    private String gameTitle;
    private String gameDescription;
    private String genre;
    private LocalDate releasedOn;
    private String gameScreenshotUrl;

    public GameViewModel() {
    }

    public Long getId() {
        return id;
    }

    public GameViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public GameViewModel setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public GameViewModel setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public GameViewModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public GameViewModel setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
        return this;
    }

    public String getGameScreenshotUrl() {
        return gameScreenshotUrl;
    }

    public GameViewModel setGameScreenshotUrl(String gameScreenshotUrl) {
        this.gameScreenshotUrl = gameScreenshotUrl;
        return this;
    }
}
