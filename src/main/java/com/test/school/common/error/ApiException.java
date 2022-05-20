package com.test.school.common.error;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ExceptionEnum errorEnum;

    private ApiExceptionEntity errorEntity;
    private final String data;

    public ApiException(ExceptionEnum error) {
        super(error.getMessage());
        this.errorEnum = error;
        this.data = null;
    }

    public ApiException(ApiExceptionEntity errorEntity){
        this.data = null;
        this.errorEntity = errorEntity;
    }
}
