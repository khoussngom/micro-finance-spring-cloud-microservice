package com.khouss.transfert.dtos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SourceNotEqualDestinationValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SourceNotEqualDestination {
    String message() default "Le compte source ne peut pas être le même que le compte destination";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}