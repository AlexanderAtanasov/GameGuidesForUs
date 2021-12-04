package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.model.view.GuideListViewModel;
import com.example.gameGuidesForUs.model.view.GuideViewModel;
import com.example.gameGuidesForUs.service.impl.OnlineUser;

import java.util.List;

public interface GuideService {
    List<GuideListViewModel> findAllGuidesForGivenGame(Long id);

    boolean isGuideTitleFree(String guideTitle);


    void addGuide(GuideAddServiceModel newGuide, Long gameId, String userIdentifier);

    GuideViewModel findGuideById(Long id);

    void deleteGuide(Long id);

    Long getGameOfTheGuide(Long id);

    Boolean findIfUserIsAdminOrOwner(String currentUser, Long id);
}
