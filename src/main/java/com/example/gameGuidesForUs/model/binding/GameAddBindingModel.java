package com.example.gameGuidesForUs.model.binding;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;
import com.example.gameGuidesForUs.model.validator.UniqueGameTitle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class GameAddBindingModel {

    private String gameTitle;
    private GenreEnum genre;
    private String gameDescription;
    private LocalDate releasedOn;
    private MultipartFile screenshotUrl;

    public GameAddBindingModel() {
    }

    @NotBlank(message = "Game Title cannot be blank.")
    @Size(min = 3, max = 35, message = "Game title must be between 3 and 35 characters.")
    @UniqueGameTitle
    public String getGameTitle() {
        return gameTitle;
    }

    public GameAddBindingModel setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
        return this;
    }

    @NotNull
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


    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    public GameAddBindingModel setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
        return this;
    }


    @NotNull(message = "Add screenshot.")
    public MultipartFile getScreenshotUrl() {
        return screenshotUrl;
    }

    public GameAddBindingModel setScreenshotUrl(MultipartFile screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
        return this;
    }
}
