package net.isbg.people.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonNameValidator.class)
public @interface PersonName {
    String message() default "The name is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}