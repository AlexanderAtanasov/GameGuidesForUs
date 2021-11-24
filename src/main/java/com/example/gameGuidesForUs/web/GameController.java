package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GameAddBindingModel;
import com.example.gameGuidesForUs.model.binding.UserRegistrationBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class GameController {

    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel() {
        return new GameAddBindingModel();
    }

    @GetMapping("/games/add")
    public String gameAdd() {
        return "game-add";
    }

    @PostMapping("/games/add")
    public String gameAddConfirm(@Valid GameAddBindingModel gameAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gameAddBindingModel", gameAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.gameAddBindingModel",
                            bindingResult);
            return "redirect:game-add";
        }


        return "redirect:games";
    }
}
