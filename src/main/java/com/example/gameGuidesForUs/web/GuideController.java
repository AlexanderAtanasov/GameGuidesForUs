package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.service.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @GetMapping("/guides/{id}/view/")
    public String viewGuide(@PathVariable Long id, Model model) {

        model.addAttribute("listOfGuides", guideService.findAllGuidesForGivenGame(id));

        return "guide-view";
    }
}
