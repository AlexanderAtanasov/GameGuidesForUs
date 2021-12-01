package com.example.gameGuidesForUs.model.entity;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private String gameTitle;
    private String gameDescription;
    private GenreEnum genre;
    private LocalDate releasedOn;
    private String gameScreenshotUrl;
    private List<Guide> guides;

    @OneToMany(mappedBy = "gameId", cascade = CascadeType.REMOVE,  orphanRemoval = true)
    public List<Guide> getGuides() {
        return guides;
    }

    public Game setGuides(List<Guide> guides) {
        this.guides = guides;
        return this;
    }

    public Game() {
    }

    @Column(unique = true, nullable = false)
    public String getGameTitle() {
        return gameTitle;
    }

    public Game setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    public String getGameDescription() {
        return gameDescription;
    }

    public Game setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
        return this;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public GenreEnum getGenre() {
        return genre;
    }

    public Game setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public Game setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
        return this;
    }

    public String getGameScreenshotUrl() {
        return gameScreenshotUrl;
    }

    public Game setGameScreenshotUrl(String gameScreenshotUrl) {
        this.gameScreenshotUrl = gameScreenshotUrl;
        return this;
    }


}
