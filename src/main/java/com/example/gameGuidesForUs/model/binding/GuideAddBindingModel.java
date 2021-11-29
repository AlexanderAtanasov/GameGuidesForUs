package com.example.gameGuidesForUs.model.binding;

import com.example.gameGuidesForUs.model.validator.UniqueGuideTitle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GuideAddBindingModel {

    private String guideTitle;
    private String description;
    private Long id;

    public Long getId() {
        return id;
    }

    public GuideAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @Size(min =3, max = 35, message = "Guide Title Must be between 3 and 35 characters.")
    @UniqueGuideTitle
    @NotBlank(message = "Guide Title cannot be blank.")
    public String getGuideTitle() {
        return guideTitle;
    }

    public GuideAddBindingModel setGuideTitle(String guideTitle) {
        this.guideTitle = guideTitle;
        return this;
    }

    @Size(min = 15, message = "Description must be more than 15 characters.")
    public String getDescription() {
        return description;
    }

    public GuideAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }



}
