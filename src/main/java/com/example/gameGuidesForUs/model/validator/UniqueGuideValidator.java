package com.example.gameGuidesForUs.model.validator;

import com.example.gameGuidesForUs.service.GuideService;
import com.example.gameGuidesForUs.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueGuideValidator  implements ConstraintValidator<UniqueGuideTitle, String> {

    private final GuideService guideService;

    public UniqueGuideValidator(GuideService guideService) {
        this.guideService = guideService;
    }

    @Override
    public boolean isValid(String guideTitle, ConstraintValidatorContext context) {
        if (guideTitle == null) {
            return true;
        }
        return guideService.isGuideTitleFree(guideTitle);
    }
}
