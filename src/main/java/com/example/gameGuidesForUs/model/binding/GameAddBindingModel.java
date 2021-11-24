package com.example.gameGuidesForUs.model.binding;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.validator.UniqueGameTitle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class GameAddBindingModel {

    private String gameTitle;
    private GenreEnum genre;
    private String gameDescription;
    private String gameScreenshotUrl;
    private LocalDate releasedOn;

    public GameAddBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 35, message = "Game title must be between 3 and 35 characters.")
    @UniqueGameTitle
    public String getGameTitle() {
        return gameTitle;
    }

    public GameAddBindingModel setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    @NotBlank
    public GenreEnum getGenre() {
        return genre;
    }

    public GameAddBindingModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @NotBlank
    @Size(min = 15, message = "Game description must be more than 15 characters.")
    public String getGameDescription() {
        return gameDescription;
    }

    public GameAddBindingModel setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
        return this;
    }

    public String getGameScreenshotUrl() {
        return gameScreenshotUrl;
    }

    public GameAddBindingModel setGameScreenshotUrl(String gameScreenshotUrl) {
        this.gameScreenshotUrl = gameScreenshotUrl;
        return this;
    }

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public GameAddBindingModel setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
        return this;
    }

}
