package com.test.school.common.annotation.validator;

import com.test.school.common.annotation.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return Pattern.matches("^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]*$", value);
    }
}