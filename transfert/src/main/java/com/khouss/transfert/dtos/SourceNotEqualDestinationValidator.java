package com.khouss.transfert.dtos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SourceNotEqualDestinationValidator implements ConstraintValidator<SourceNotEqualDestination, TransfertRequest> {

    @Override
    public void initialize(SourceNotEqualDestination constraintAnnotation) {
    }

    @Override
    public boolean isValid(TransfertRequest request, ConstraintValidatorContext context) {
        if (request == null || request.getSource() == null || request.getDestination() == null) {
            return true;
        }
        return !request.getSource().equals(request.getDestination());
    }
}