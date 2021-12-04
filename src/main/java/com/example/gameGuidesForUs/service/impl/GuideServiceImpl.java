package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.User;
import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.model.view.GuideListViewModel;
import com.example.gameGuidesForUs.model.view.GuideViewModel;
import com.example.gameGuidesForUs.repository.CommentRepository;
import com.example.gameGuidesForUs.repository.GameRepository;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.web.exception.ObjectNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .map(guide -> modelMapper.map(guide, GuideViewModel.class)).orElseThrow(ObjectNotFound::new);

    }

    @Override
    public void deleteGuide(Long id) {
        guideRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Long getGameOfTheGuide(Long id) {
        return guideRepository.getById(id).getGameId().getId();
    }

    @Override
    public Boolean findIfUserIsAdminOrOwner(String currentUser, Long id) {

        User current = userRepository.findByUsername(currentUser).get();
        Long userId = userRepository.findByUsername(currentUser).get().getId();
        Long ownerId = guideRepository.findById(id).get().getGuideCreatedBy().getId();
        return isAdmin(current) || userId == ownerId;
    }

    private boolean isAdmin(User user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).anyMatch(r-> r.equals(UserRoleEnum.ADMIN));
    }

}
