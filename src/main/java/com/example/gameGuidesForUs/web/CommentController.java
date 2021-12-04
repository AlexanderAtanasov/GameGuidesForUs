package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.CommentAddBindingModel;
import com.example.gameGuidesForUs.service.CommentService;
import com.example.gameGuidesForUs.service.UserService;
import com.example.gameGuidesForUs.service.impl.OnlineUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;


    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @ModelAttribute
    public CommentAddBindingModel commentAddBindingModel() {
        return  new CommentAddBindingModel();
    }




    @DeleteMapping("/comments/{guideId}/delete/{commentId}")
    public String DeleteComment(@PathVariable Long guideId, @PathVariable Long commentId,
     @AuthenticationPrincipal OnlineUser currentUser){

        if (userService.checkIfUserIsAdmin(currentUser)) {
            commentService.deleteComment(commentId);
        }
    //TODO add unathorized error

        return "redirect:/games/guides/{guideId}/view";
    }


    //TODO CREATE UPDATE COMMENT SECTION FOR ONLY ADMIN OR CREATOR






}
