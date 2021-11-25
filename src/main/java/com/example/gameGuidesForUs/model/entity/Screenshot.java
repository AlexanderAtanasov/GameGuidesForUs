package com.example.gameGuidesForUs.model.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "screenshots")
public class Screenshot extends BaseEntity{


    private String url;
    private String publicId;
    private User uploadedBy;
    private Instant uploadedOn;

    public Screenshot() {
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getUrl() {
        return url;
    }

    public Screenshot setUrl(String url) {
        this.url = url;
        return this;
    }
    @Column(unique = true, nullable = false, columnDefinition = "TEXT")
    public String getPublicId() {
        return publicId;
    }

    public Screenshot setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    @ManyToOne
    public User getUploadedBy() {
        return uploadedBy;
    }

    public Screenshot setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
        return this;
    }

    @Column(nullable = false)
    public Instant getUploadedOn() {
        return uploadedOn;
    }

    public Screenshot setUploadedOn(Instant uploadedOn) {
        this.uploadedOn = uploadedOn;
        return this;
    }

    @PrePersist
    public void beforeCreate() {
        setUploadedOn(Instant.now());
    }
}
