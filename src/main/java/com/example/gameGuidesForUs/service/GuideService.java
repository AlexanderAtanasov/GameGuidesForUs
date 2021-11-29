package com.example.gameGuidesForUs.service;

import com.example.gameGuidesForUs.model.view.GuideListViewModel;

import java.util.List;

public interface GuideService {
    List<GuideListViewModel> findAllGuidesForGivenGame(Long id);
}
