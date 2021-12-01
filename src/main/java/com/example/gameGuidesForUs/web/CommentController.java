package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.CommentAddBindingModel;
import com.example.gameGuidesForUs.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ModelAttribute
    public CommentAddBindingModel commentAddBindingModel() {
        return  new CommentAddBindingModel();
    }

    @DeleteMapping("/comments/{guideId}/delete/{commentId}")
    public String DeleteComment(@PathVariable Long guideId, @PathVariable Long commentId){


        commentService.deleteComment(commentId);

        return "redirect:/guides/{guideId}/view";
    }
}
