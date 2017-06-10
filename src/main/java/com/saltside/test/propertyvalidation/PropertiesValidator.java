package com.saltside.test.propertyvalidation;

import com.saltside.test.entity.Bird;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolationException;

/**
 * Validate the property of input
 * Created by krsna on 04/06/2017.
 */
public class PropertiesValidator {

    public static void validate(Bird bird) {

        if (bird.getName() == null) {
            throw new ConstraintViolationException("Name field missing", null);
        }
        if (bird.getFamily() == null) {
            throw new ConstraintViolationException("Family field missing", null);
        }
        if (bird.getContinents() == null) {
            throw new ConstraintViolationException("Continents field missing", null);
        }

    }

}
