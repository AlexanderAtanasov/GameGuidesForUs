package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.model.view.GameViewModel;
import com.example.gameGuidesForUs.model.view.HomeViewModel;

import java.util.List;

public interface GameService {


    long getTotalGameCount();

    List<GameViewModel> findAllGamesSortByReleaseDateDesc();

    void deleteGame(Long id);

    boolean isGameTitleFree(String gameTitle);

    void addGame(GameAddServiceModel gameAddServiceModel);

    String findGameScreenshotId(Long id);

    GameViewModel findGameInformationById(Long id);

    HomeViewModel getAllGamesForRandomShow();
}
