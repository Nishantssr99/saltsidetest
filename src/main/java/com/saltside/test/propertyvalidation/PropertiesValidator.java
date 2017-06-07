package com.saltside.test.propertyvalidation;

import com.saltside.test.entity.Bird;
import com.saltside.test.entity.Properties;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Created by krsna on 04/06/2017.
 */
public class PropertiesValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Bird.class;
    }

    @Override
    public void validate(Object o, Errors errors) {

        Bird bird = (Bird) o;
        Properties properties = bird.getProperties();
        if (properties.getName() == null) {
            errors.rejectValue("name", "Invalid input");
            throw new ConstraintViolationException("Name field missing", null);
        }
        if (properties.getFamily() == null) {
            errors.rejectValue("family", "Invalid input");
            throw new ConstraintViolationException("Family field missing", null);
        }
        if (properties.getContinents() == null) {
            errors.rejectValue("continents", "Invalid input");
            throw new ConstraintViolationException("Continents field missing", null);
        }
    }

    public static void validate(Bird bird) {
        Properties properties = bird.getProperties();
        if (properties.getName() == null) {
            throw new ConstraintViolationException("Name field missing", null);
        }
        if (properties.getFamily() == null) {
            throw new ConstraintViolationException("Family field missing", null);
        }
        if (properties.getContinents() == null) {
            throw new ConstraintViolationException("Continents field missing", null);
        }

    }

}
