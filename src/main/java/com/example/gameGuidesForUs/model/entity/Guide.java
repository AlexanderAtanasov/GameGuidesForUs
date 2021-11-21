package com.example.gameGuidesForUs.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "guides")
public class Guide extends BaseEntity {

    private String guideTitle;
    private String description;
    private User guideCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;
    private List<Comment> comments;

    public Guide() {
    }

    @Column(nullable = false, unique = true)
    public String getGuideTitle() {
        return guideTitle;
    }

    public Guide setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Guide setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne
    public User getGuideCreatedBy() {
        return guideCreatedBy;
    }

    public Guide setGuideCreatedBy(User guideCreatedBy) {
        this.guideCreatedBy = guideCreatedBy;
        return this;
    }

    @Column(nullable = false)
    public Instant getCreatedOn() {
        return createdOn;
    }

    public Guide setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public Guide setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    @OneToMany
    public List<Comment> getComments() {
        return comments;
    }

    public Guide setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    @PrePersist
    public void beforeCreate() {
        setCreatedOn(Instant.now());
    }

    @PostPersist void onUpdate() {
        setModifiedOn(Instant.now());
    }
}