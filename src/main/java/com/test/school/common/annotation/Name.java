package com.test.school.common.annotation;

import com.test.school.common.annotation.validator.NameValidator;
import com.test.school.common.annotation.validator.TelValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
    String message() default "이름";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
