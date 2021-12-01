package com.example.gameGuidesForUs.web;

import com.example.gameGuidesForUs.model.binding.CommentAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @ModelAttribute
    public CommentAddBindingModel commentAddBindingModel() {
        return  new CommentAddBindingModel();
    }


}
