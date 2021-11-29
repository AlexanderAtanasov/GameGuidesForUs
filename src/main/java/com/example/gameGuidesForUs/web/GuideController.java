package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GuideAddBindingModel;
import com.example.gameGuidesForUs.service.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }


    @ModelAttribute
    public GuideAddBindingModel guideAddBindingModel() {
        return new GuideAddBindingModel();
    }

    @GetMapping("/guides/add")
    public String addGuide() {
        return "guide-add";
    }

    @PostMapping("/guides/add")
    public String addGuideConfirm(@Valid GuideAddBindingModel guideAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {


        return "/games/";
    }



    @GetMapping("/guides/{id}/view/")
    public String viewGuide(@PathVariable Long id, Model model) {
        return "guide-view";
    }
}
