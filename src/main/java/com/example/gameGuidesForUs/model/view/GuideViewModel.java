package com.example.gameGuidesForUs.model.view;

import com.example.gameGuidesForUs.model.entity.Comment;
import com.example.gameGuidesForUs.model.entity.Game;
import com.example.gameGuidesForUs.model.entity.User;

import java.time.Instant;
import java.util.List;

public class GuideViewModel {

    private Long id;
    private String guideTitle;
    private String description;
    private User guideCreatedBy;
    private Instant createdOn;
    private Game gameId;

    public String getGuideTitle() {
        return guideTitle;
    }

    public GuideViewModel setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GuideViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getGuideCreatedBy() {
        return guideCreatedBy;
    }

    public GuideViewModel setGuideCreatedBy(User guideCreatedBy) {
        this.guideCreatedBy = guideCreatedBy;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public GuideViewModel setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Game getGameId() {
        return gameId;
    }

    public GuideViewModel setGameId(Game gameId) {
        this.gameId = gameId;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GuideViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
