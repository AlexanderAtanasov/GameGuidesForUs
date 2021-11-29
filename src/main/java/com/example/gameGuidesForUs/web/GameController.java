package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.GameAddBindingModel;
import com.example.gameGuidesForUs.model.service.GameAddServiceModel;
import com.example.gameGuidesForUs.service.GameService;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final GuideService guideService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;


    public GameController(GameService gameService, GuideService guideService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.gameService = gameService;
        this.guideService = guideService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @ModelAttribute
    public GameAddBindingModel gameAddBindingModel() {
        return new GameAddBindingModel();
    }

    @GetMapping("/add")
    public String gameAdd(Model model) {
        if (!model.containsAttribute("emptyScreenshot")) {
            model.addAttribute("emptyScreenshot", false);
        }
        return "game-add";
    }

    @PostMapping("/add")
    public String gameAddConfirm(@Valid GameAddBindingModel gameAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("gameAddBindingModel", gameAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.gameAddBindingModel",
                            bindingResult);
            if (gameAddBindingModel.getScreenshotUrl().isEmpty()) {
                redirectAttributes.addFlashAttribute("gameAddBindingModel", gameAddBindingModel)
                        .addFlashAttribute("emptyScreenshot", true);
            }

            return "redirect:/games/add";
        }




        String screenshotUrl = cloudinaryService.upload(gameAddBindingModel.getScreenshotUrl()).getUrl();

        GameAddServiceModel gameAddServiceModel = modelMapper.map(gameAddBindingModel, GameAddServiceModel.class);
        gameAddServiceModel.setGameScreenshotUrl(screenshotUrl);
        gameService.addGame(gameAddServiceModel);
        return "redirect:/games/all";
    }

    @GetMapping("/all")
    public String viewGames(Model model) {
        model.addAttribute("totalGames", gameService.getTotalGameCount());
        model.addAttribute("allGamesPreview", gameService.findAllGamesSortByReleaseDateDesc());
        return "games";
    }

    @GetMapping("/{id}/view")
    public String gameView(@PathVariable Long id, Model model) {
        model.addAttribute("gameInformation",gameService.findGameInformationById(id));
        model.addAttribute("listOfGuides", guideService.findAllGuidesForGivenGame(id));
        return "game-view";
    }

    @DeleteMapping("/{id}/delete")
    public String gameDelete(@PathVariable Long id) {
        cloudinaryService.delete(gameService.findGameScreenshotId(id));
        gameService.deleteGame(id);
        return "redirect:/games/all";
    }



}
