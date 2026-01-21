package com.khouss.transfert.dtos;

import com.khouss.transfert.openFeign.CompteClient;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountValidator implements ConstraintValidator<ValidAccount, String> {

    @Autowired
    private CompteClient compteClient;

    @Override
    public void initialize(ValidAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(String numeroTelephone, ConstraintValidatorContext context) {
        if (numeroTelephone == null) {
            return false;
        }
        try {
            return compteClient.compteExists(numeroTelephone);
        } catch (Exception e) {
            return false;
        }
    }
}