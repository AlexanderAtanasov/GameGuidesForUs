package com.example.gameGuidesForUs.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class CommentUpdateBindingModel {

    private Long commentId;
    private Long guideId;
    private String comment;
    private MultipartFile screenshot;

    public Long getCommentId() {
        return commentId;
    }

    public CommentUpdateBindingModel setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public Long getGuideId() {
        return guideId;
    }

    public CommentUpdateBindingModel setGuideId(Long guideId) {
        this.guideId = guideId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CommentUpdateBindingModel setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public MultipartFile getScreenshot() {
        return screenshot;
    }

    public CommentUpdateBindingModel setScreenshot(MultipartFile screenshot) {
        this.screenshot = screenshot;
        return this;
    }
}
