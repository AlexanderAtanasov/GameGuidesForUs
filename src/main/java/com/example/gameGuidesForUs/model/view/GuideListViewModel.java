package com.example.gameGuidesForUs.model.view;

import com.example.gameGuidesForUs.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public class GuideListViewModel {

    private Long id;
    private String guideTitle;
    private String description;
    private User guideCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;

    public Long getId() {
        return id;
    }

    public GuideListViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGuideTitle() {
        return guideTitle;
    }

    public GuideListViewModel setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GuideListViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getGuideCreatedBy() {
        return guideCreatedBy;
    }

    public GuideListViewModel setGuideCreatedBy(User guideCreatedBy) {
        this.guideCreatedBy = guideCreatedBy;
        return this;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Instant getCreatedOn() {
        return createdOn;
    }

    public GuideListViewModel setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public GuideListViewModel setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }
}
