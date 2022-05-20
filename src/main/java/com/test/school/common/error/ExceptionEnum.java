package com.test.school.common.error;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {

    BAD_REQUEST_BODY("BAD_REQUEST_BODY","파라미터 오류");
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status,String code){
        this.code = code;
    }

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
