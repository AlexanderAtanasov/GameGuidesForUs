package com.example.gameGuidesForUs.service.impl;

import com.example.gameGuidesForUs.model.view.GuideListViewModel;
import com.example.gameGuidesForUs.repository.GuideRepository;
import com.example.gameGuidesForUs.service.GuideService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideServiceImpl implements GuideService {

    private final ModelMapper modelMapper;
    private final GuideRepository guideRepository;


    public GuideServiceImpl(ModelMapper modelMapper, GuideRepository guideRepository) {
        this.modelMapper = modelMapper;
        this.guideRepository = guideRepository;
    }


    @Override
    public List<GuideListViewModel> findAllGuidesForGivenGame(Long id) {
        return guideRepository
                .findAllByGameId(id)
                .stream()
                .map(guide -> modelMapper.map(guide, GuideListViewModel.class))
                .collect(Collectors.toList());

    }
}
