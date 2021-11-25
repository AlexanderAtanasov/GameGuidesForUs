package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GameAddBindingModel;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final ModelMapper modelMapper;


    public GameController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel() {
        return new GameAddBindingModel();
    }

    @GetMapping("/add")
    public String gameAdd() {
//        if (!model.containsAttribute("gameAddBindingModel")) {
//            model.
//                    addAttribute("gameAddBindingModel", gameAddBindingModel());
//
//        }
        return "game-add";
    }

    @PostMapping("/add")
    public String gameAddConfirm(@Valid GameAddBindingModel gameAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gameAddBindingModel", gameAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.gameAddBindingModel",
                            bindingResult);
            return "redirect:/games/add";
        }
        gameService.addGame(modelMapper.map(gameAddBindingModel, GameAddServiceModel.class));
        //todo FINISH GAMEADD AND GAMEUPDATE

        return "redirect:/";
    }

    @GetMapping("/all")
    public String viewGames(Model model) {
        model.addAttribute("totalGames", gameService.getTotalGameCount());
        model.addAttribute("allGamesPreview", gameService.findAllGamesSortByReleaseDateDesc());
        return "games";
    }

    @DeleteMapping("/{id}/delete/")
    public String gameDelete(@PathVariable Long id) {
        gameService.deleteGame(id);
        return "redirect:/games/all";
    }

    @GetMapping("/{id}/update/")
    public String gameUpdate(@PathVariable Long id) {
//TODO
        return "game-update";
    }

    @PatchMapping("/{id}/update/")
    public String gameUpdateConfirm(@PathVariable Long id) {
//TODO
        return "redirect:/games/all";
    }
}
