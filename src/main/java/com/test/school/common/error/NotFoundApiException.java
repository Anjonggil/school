package com.test.school.common.error;


import lombok.Getter;

@Getter
public class NotFoundApiException extends RuntimeException{

    private ApiExceptionEntity errorEntity;

    public NotFoundApiException(ApiExceptionEntity error) {
        super(error.getErrorInfo().getMessage());
        this.errorEntity = error;
    }
}