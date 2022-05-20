package com.test.school.common.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@NoArgsConstructor
public class ApiExceptionEntity {
    private String code;
    private String message;

    @Builder
    public ApiExceptionEntity(String errorCode, String errorMessage){
        this.code = errorCode;
        this.message = errorMessage;
    }
}