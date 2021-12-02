package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @GetMapping("/statistics")
    public ModelAndView statistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statisticService.getStats());
        modelAndView.setViewName("statistics");
        return modelAndView;
    }
}
