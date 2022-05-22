package com.test.school.common.annotation.validator;

import com.test.school.common.annotation.Type;
import com.test.school.domain.SchoolType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SchoolTypeValidator implements ConstraintValidator <Type, SchoolType> {

    @Override
    public boolean isValid(SchoolType value, ConstraintValidatorContext context) {
        if (value == null){
            return false;
        }

        List<String> schoolTypeList = new ArrayList<>();
        schoolTypeList.add("ELEMENTARY");
        schoolTypeList.add("MIDDLE");
        schoolTypeList.add("HIGH");

        return schoolTypeList.contains(value.getCode());
    }
}
