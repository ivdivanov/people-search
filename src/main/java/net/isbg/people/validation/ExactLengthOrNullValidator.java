package net.isbg.people.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExactLengthOrNullValidator implements ConstraintValidator<ExactLengthOrNull, String> {

    private int length;

    @Override
    public void initialize(ExactLengthOrNull constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.length() == length;
    }
}
