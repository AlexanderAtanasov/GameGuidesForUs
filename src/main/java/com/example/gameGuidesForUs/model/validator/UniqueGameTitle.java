package com.example.gameGuidesForUs.model.validator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueGameTitleValidator.class)

public @interface UniqueGameTitle {

    String message() default "Game already exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
