package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.model.view.GuideListViewModel;
import com.example.gameGuidesForUs.model.view.GuideViewModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.GuideService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuideServiceImpl implements GuideService {

    private final ModelMapper modelMapper;
    private final GuideRepository guideRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final CommentRepository commentRepository;


    public GuideServiceImpl(ModelMapper modelMapper, GuideRepository guideRepository,
                            UserRepository userRepository, GameRepository gameRepository,
                            CommentRepository commentRepository) {
        this.modelMapper = modelMapper;
        this.guideRepository = guideRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.commentRepository = commentRepository;

    }


    @Override
    public List<GuideListViewModel> findAllGuidesForGivenGame(Long id) {
        return guideRepository
                .findAllByGameId(id)
                .stream()
                .map(guide -> modelMapper.map(guide, GuideListViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public boolean isGuideTitleFree(String guideTitle) {
        return guideRepository.findByGuideTitle(guideTitle).isEmpty();
    }

    @Override
    public void addGuide(GuideAddServiceModel newGuide, Long gameId, String userIdentifier) {

        Guide guide = modelMapper.map(newGuide, Guide.class);
        guide.setGuideCreatedBy(userRepository.findByUsername(userIdentifier).orElse(null))
                .setGameId(gameRepository.findById(gameId).orElse(null));
        guideRepository.save(guide);
    }

    @Override
    public GuideViewModel findGuideById(Long id) {


        return guideRepository
                .findById(id)
                .map(guide -> modelMapper.map(guide, GuideViewModel.class)).orElse(null);
        //TODO FIX MUTLIPLEROLES USERS OF HAVING DUPLICATE COMMENTS
        //TODO RETURN 404 PAGE

    }

}
