package com.khouss.transfert.dtos;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AccountValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAccount {
    String message() default "Le numéro de téléphone ne correspond pas à un compte existant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}