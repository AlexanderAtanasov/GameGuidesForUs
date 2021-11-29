package com.example.gameGuidesForUs.model.service;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GuideAddServiceModel {


    private String guideTitle;
    private String description;
    private String guideCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;
    private List<Comment> comments = new ArrayList<>();
    private Long gameId;

    public String getGuideTitle() {
        return guideTitle;
    }

    public GuideAddServiceModel setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GuideAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGuideCreatedBy() {
        return guideCreatedBy;
    }

    public GuideAddServiceModel setGuideCreatedBy(String guideCreatedBy) {
        this.guideCreatedBy = guideCreatedBy;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public GuideAddServiceModel setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public GuideAddServiceModel setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public GuideAddServiceModel setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Long getGameId() {
        return gameId;
    }

    public GuideAddServiceModel setGameId(Long gameId) {
        this.gameId = gameId;
        return this;
    }
}
