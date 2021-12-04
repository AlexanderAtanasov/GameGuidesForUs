package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.model.view.GameViewModel;
import com.example.gameGuidesForUs.model.view.HomeViewModel;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.service.GameService;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    }

    @Override
    public String findGameScreenshotId(Long id) {
        String screenshotCloudinaryID = gameRepository.findById(id).orElseThrow(ObjectNotFound::new).getGameScreenshotUrl();
        screenshotCloudinaryID = screenshotCloudinaryID
                .substring(screenshotCloudinaryID.lastIndexOf("/") + 1,
                        screenshotCloudinaryID.lastIndexOf("."));

        return screenshotCloudinaryID;
    }

    @Override
    public GameViewModel findGameInformationById(Long id) {
        return modelMapper.map(gameRepository.findById(id).orElseThrow(ObjectNotFound::new), GameViewModel.class);
    }

    @Override
    public HomeViewModel getAllGamesForRandomShow() {

        if(gameRepository.findAll().size() >0 ) {
            List<HomeViewModel> homeViewModelList = gameRepository.findAll().stream()
                    .map(game -> modelMapper.map(game, HomeViewModel.class)).collect(Collectors.toList());
            return homeViewModelList.get(ThreadLocalRandom.current().nextInt(0, homeViewModelList.size()));
        }

       else {
           return new HomeViewModel();
        }


    }
}
