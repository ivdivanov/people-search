package net.isbg.people.validation;

import java.util.regex.Pattern;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PersonNameValidator implements ConstraintValidator<PersonName, String> {

    private static final String NAME_PATTERN = "^(?:(?:[A-Za-z][A-Za-z\\s-]*[A-Za-z])|(?:[\\u0400-\\u04FF][\\u0400-\\u04FF\\s-]*[\\u0400-\\u04FF]))$";

    @Override
    public boolean isValid(String valueString, ConstraintValidatorContext context) {
        if (valueString == null) {
            return false;
        }
        return Pattern.matches(NAME_PATTERN, valueString);
    }
    
}
