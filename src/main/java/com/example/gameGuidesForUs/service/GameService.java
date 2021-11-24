package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.view.GameViewModel;

import java.util.List;

public interface GameService {


    long getTotalGameCount();

    List<GameViewModel> findAllGamesSortByReleaseDateDesc();

    void deleteGame(Long id);

    boolean isGameTitleFree(String gameTitle);
}
