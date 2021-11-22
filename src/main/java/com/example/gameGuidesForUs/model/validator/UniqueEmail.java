package com.example.gameGuidesForUs.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)

public @interface UniqueEmail {

    String message() default "Email is already taken.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}