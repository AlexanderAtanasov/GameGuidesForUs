package com.example.gameGuidesForUs.model.validator;

import com.example.gameGuidesForUs.service.GameService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueGameTitleValidator implements ConstraintValidator<UniqueGameTitle, String> {

    private final GameService gameService;

    public UniqueGameTitleValidator(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public boolean isValid(String gameTitle, ConstraintValidatorContext context) {
        if (gameTitle == null) {
            return true;
        }
        return gameService.isGameTitleFree(gameTitle);
    }
}
