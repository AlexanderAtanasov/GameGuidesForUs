package com.example.gameGuidesForUs.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserNameValidator.class)

public @interface UniqueUsername {

    String message() default "Username is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
