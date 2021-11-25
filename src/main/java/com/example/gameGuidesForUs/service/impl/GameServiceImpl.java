package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.model.view.GameViewModel;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getTotalGameCount() {
        return gameRepository.count();
    }

    @Override
    public List<GameViewModel> findAllGamesSortByReleaseDateDesc() {


        return gameRepository.findAllGamesSortByReleaseDate()
                .stream().map(game -> modelMapper.map(game, GameViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public boolean isGameTitleFree(String gameTitle) {
        return gameRepository.findByGameTitle(gameTitle).isEmpty();
    }

    @Override
    public void addGame(GameAddServiceModel gameAddServiceModel) {
        Game newGame = modelMapper.map(gameAddServiceModel, Game.class);
        gameRepository.save(newGame);



        //todo try to get ID so you could attach screenshot to it.
    }
}
