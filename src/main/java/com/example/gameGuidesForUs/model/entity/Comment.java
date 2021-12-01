package com.example.gameGuidesForUs.model.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private Guide guide;
    private String comment;
    private User commentCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;
    private Screenshot screenshot;

    public Comment() {
    }

    @ManyToOne
    public Guide getGuide() {
        return guide;
    }

    public Comment setGuide(Guide guideId) {
        this.guide = guideId;
        return this;
    }

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    public String getComment() {
        return comment;
    }

    public Comment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @ManyToOne
    public User getCommentCreatedBy() {
        return commentCreatedBy;
    }

    public Comment setCommentCreatedBy(User commentCreatedBy) {
        this.commentCreatedBy = commentCreatedBy;
        return this;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Comment setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Instant getModifiedOn() {
        return modifiedOn;
    }

    public Comment setModifiedOn(Instant modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }


    @ManyToOne
    public Screenshot getScreenshot() {
        return screenshot;
    }

    public Comment setScreenshot(Screenshot screenshot) {
        this.screenshot = screenshot;
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
