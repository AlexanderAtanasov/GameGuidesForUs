package com.example.gameGuidesForUs.model.validator;

import com.example.gameGuidesForUs.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator  implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return true;
        }
        return userService.isUserNameFree(username);
    }
}
