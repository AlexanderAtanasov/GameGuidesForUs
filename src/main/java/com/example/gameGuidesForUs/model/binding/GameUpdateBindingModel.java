package com.example.gameGuidesForUs.model.binding;

import com.example.gameGuidesForUs.model.entity.enums.GenreEnum;

import java.time.LocalDate;

public class GameUpdateBindingModel {

    private Long id;
    private String gameTitle;
    private GenreEnum genre;
    private String gameDescription;
    private String gameScreenshotUrl;
    private LocalDate releasedOn;
}
