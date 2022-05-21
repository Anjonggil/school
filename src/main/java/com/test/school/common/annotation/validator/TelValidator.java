package com.test.school.common.annotation.validator;

import com.test.school.common.annotation.Tel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelValidator implements ConstraintValidator<Tel, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
