package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GameAddBindingModel;
import com.example.gameGuidesForUs.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel() {
        return new GameAddBindingModel();
    }

    @GetMapping("/add")
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

        return "redirect:/";
    }

    @GetMapping("/all")
    public String viewGames(Model model) {
        model.addAttribute("totalGames", gameService.getTotalGameCount());
        model.addAttribute("allGamesPreview", gameService.findAllGamesSortByReleaseDateDesc());
        return "games";
    }

    @DeleteMapping("/delete/{id}")
    public String gameDelete(@PathVariable Long id) {
        gameService.deleteGame(id);

        return "redirect:/games/all";
    }

    @PatchMapping("/update/{id}")
    public String gameUpdate(@PathVariable Long id) {

        return "redirect:/games/all";
    }
}
