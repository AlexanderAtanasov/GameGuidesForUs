package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

public class CommentAddServiceModel {

    private String comment;
    private Screenshot screenshot;




    public CommentAddServiceModel() {
    }

    public String getComment() {
        return comment;
    }

    public CommentAddServiceModel setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Screenshot getScreenshot() {
        return screenshot;
    }

    public CommentAddServiceModel setScreenshot(Screenshot screenshot) {
        this.screenshot = screenshot;
        return this;
    }
}