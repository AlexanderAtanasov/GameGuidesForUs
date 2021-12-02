package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.CommentAddBindingModel;
import com.example.gameGuidesForUs.model.binding.GuideAddBindingModel;
import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.model.service.CommentAddServiceModel;
import com.example.gameGuidesForUs.model.service.GuideAddServiceModel;
import com.example.gameGuidesForUs.model.view.CommentViewModel;
import com.example.gameGuidesForUs.repository.UserRepository;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.ScreenshotService;
import com.example.gameGuidesForUs.service.cloudinary.CloudinaryService;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GuideController {

    private final GuideService guideService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;
    private final ScreenshotService screenshotService;

    public GuideController(GuideService guideService, CommentService commentService, ModelMapper modelMapper, CloudinaryService cloudinaryService, UserRepository userRepository, ScreenshotService screenshotService) {
        this.guideService = guideService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.screenshotService = screenshotService;
    }


    @ModelAttribute
    public GuideAddBindingModel guideAddBindingModel() {
        return new GuideAddBindingModel();
    }

    @ModelAttribute
    public CommentAddBindingModel commentAddBindingModel() {
        return new CommentAddBindingModel();
    }

    @GetMapping("/guides/{id}/add")
    public String addGuide(@PathVariable Long id) {
        return "guide-add";
    }

    @PostMapping("/guides/{id}/add")
    public String addGuideConfirm(@PathVariable Long id,
                                  @Valid GuideAddBindingModel guideAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal OnlineUser currentUser) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("guideAddBindingModel", guideAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.guideAddBindingModel", bindingResult);
            return "redirect:/games/guides/{id}/add";
        }

        GuideAddServiceModel newGuide = modelMapper.map(guideAddBindingModel, GuideAddServiceModel.class);

        guideService.addGuide(newGuide, id, currentUser.getUserIdentifier());
        return "redirect:/games/{id}/view";
    }

    @GetMapping("/guides/{id}/view")
    public String viewGuide(@PathVariable Long id, Model model) {
        List<CommentViewModel> byGuideId = commentService.findByGuideId(id);
        model.addAttribute("currentGuide", guideService.findGuideById(id));
        model.addAttribute("allCommentsForTheGuide", commentService.findByGuideId(id));
        return "guide-view";
    }


    @DeleteMapping("guides/{id}/delete")
    public String deleteGuide(@PathVariable Long id, Model model) {


        Long gameOfTheGuide = guideService.getGameOfTheGuide(id);
        guideService.deleteGuide(id);
        //TODO GUIDE DELETE IF USER IS OWNER OR ADMIN
        String returnString = "redirect:/games/" + gameOfTheGuide + "/view";
        return returnString;
    }


    @PostMapping("/comments/{id}/add")
    public String addComment(@PathVariable Long id,
                             @Valid CommentAddBindingModel commentAddBindingModel,
                             @AuthenticationPrincipal OnlineUser currentUser ) throws IOException {


        CommentAddServiceModel commentAddServiceModel = modelMapper.map(commentAddBindingModel, CommentAddServiceModel.class);
        if (!commentAddBindingModel.getScreenshot().isEmpty()) {
            commentAddServiceModel
                    .setScreenshot(screenshotService
                            .addScreenshot(commentAddBindingModel.getScreenshot(),
                                    currentUser.getUserIdentifier()));
        }

        commentService.addComment(commentAddServiceModel, id, currentUser.getUserIdentifier());

        return "redirect:/games/guides/{id}/view";
    }

}
