package com.test.school.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST_BODY("BAD_REQUEST_BODY"),
    ALREADY_EXIST_STUDENT("ALREADY_EXIST_STUDENT"),
    ALREADY_EXIST_SUBJECT("ALREADY_EXIST_SUBJECT"),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND"),
    SUBJECT_NOT_FOUND("SUBJECT_NOT_FOUND");

    private final String code;
//    private final String message;

    ErrorCode (String code){
        this.code = code;
    }
}
