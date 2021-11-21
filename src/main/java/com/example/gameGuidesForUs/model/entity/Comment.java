package com.example.gameGuidesForUs.model.entity;

import com.example.gameGuidesForUs.model.entity.BaseEntity;
import com.example.gameGuidesForUs.model.entity.User;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private String comment;
    private User commentCreatedBy;
    private Instant createdOn;
    private Instant modifiedOn;
    private List<Screenshot> screenshots = new ArrayList<>();

    public Comment() {
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

    @Column(nullable = false)
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

    @OneToMany
    public List<Screenshot> getScreenshots() {
        return screenshots;
    }

    public Comment setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
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
