package com.test.school.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_REQUEST_BODY("BAD_REQUEST_BODY",""),
    ALREADY_EXIST_STUDENT("ALREADY_EXIST_STUDENT","이미 존재하는 학생입니다."),
    ALREADY_EXIST_SUBJECT("ALREADY_EXIST_SUBJECT","이미 존재하는 과목입니다."),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND","학생을 찾을 수 없습니다."),
    SUBJECT_NOT_FOUND("SUBJECT_NOT_FOUND","과목을 찾을 수 없습니다.");

    private final String code;
    private final String message;

    ErrorCode (String code, String message){
        this.code = code;
        this.message = message;
    }
}
