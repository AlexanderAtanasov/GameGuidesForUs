package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GuideAddBindingModel;
import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;

@Controller
public class GuideController {

    private final GuideService guideService;
    private final ModelMapper modelMapper;

    public GuideController(GuideService guideService, ModelMapper modelMapper) {
        this.guideService = guideService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute
    public GuideAddBindingModel guideAddBindingModel() {
        return new GuideAddBindingModel();
    }

    @GetMapping("/guides/{id}/add/")
    public String addGuide(@PathVariable Long id) {
        return "guide-add";
    }

    @PostMapping("/guides/{id}/add/")
    public String addGuideConfirm(@PathVariable Long id,
                                  @Valid GuideAddBindingModel guideAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal OnlineUser currentUser) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("guideAddBindingModel", guideAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.guideAddBindingModel", bindingResult);
            return "redirect:/guides/{id}/add/";
        }

        GuideAddServiceModel newGuide = modelMapper.map(guideAddBindingModel, GuideAddServiceModel.class);

        guideService.addGuide(newGuide, id, currentUser.getUserIdentifier());
        return "redirect:/games/{id}/view/";
    }

    @GetMapping("/guides/{id}/view/")
    public String viewGuide(@PathVariable Long id, Model model) {
        return "guide-view";
    }


    //TODO GUIDE DELETE IF USER IS OWNER OR ADMIN
}
