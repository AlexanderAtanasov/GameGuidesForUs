package com.example.gameGuidesForUs.model.view;

import com.example.gameGuidesForUs.model.entity.Guide;
import com.example.gameGuidesForUs.model.entity.Screenshot;
import com.example.gameGuidesForUs.model.entity.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CommentViewModel {

    private Long id;
    private Guide guideId;
    private String comment;
    private User commentCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;
    private String screenshot;

    public Long getId() {
        return id;
    }

    public CommentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Guide getGuideId() {
        return guideId;
    }

    public CommentViewModel setGuideId(Guide guideId) {
        this.guideId = guideId;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public CommentViewModel setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public User getCommentCreatedBy() {
        return commentCreatedBy;
    }

    public CommentViewModel setCommentCreatedBy(User commentCreatedBy) {
        this.commentCreatedBy = commentCreatedBy;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public CommentViewModel setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public CommentViewModel setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public CommentViewModel setScreenshot(String screenshot) {
        this.screenshot = screenshot;
        return this;
    }


}
