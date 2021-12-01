package com.example.gameGuidesForUs.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class CommentAddBindingModel {

    private Long id;
    private String comment;
    private MultipartFile screenshot;

    public Long getId() {
        return id;
    }

    public CommentAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CommentAddBindingModel setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public MultipartFile getScreenshot() {
        return screenshot;
    }

    public CommentAddBindingModel setScreenshot(MultipartFile screenshot) {
        this.screenshot = screenshot;
        return this;
    }
}
