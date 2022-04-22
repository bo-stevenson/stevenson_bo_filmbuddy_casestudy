package com.teksystems.casestudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TitleUniqueImpl.class)
@Target( {ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TitleUnique {
    String message() default "{MovieTitleUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
