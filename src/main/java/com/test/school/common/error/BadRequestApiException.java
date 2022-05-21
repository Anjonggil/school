package com.test.school.common.error;

import lombok.Getter;

@Getter
public class BadRequestApiException extends RuntimeException{

    private ApiExceptionEntity errorEntity;

    public BadRequestApiException(ApiExceptionEntity error) {
        super(error.getErrorInfo().getMessage());
        this.errorEntity = error;
    }
}
