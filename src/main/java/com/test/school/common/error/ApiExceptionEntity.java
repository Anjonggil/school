package com.test.school.common.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@NoArgsConstructor
public class ApiExceptionEntity<T> {
    private T data;
    private ErrorInfo errorInfo;

    @Builder
    public ApiExceptionEntity(String errorCode, String errorMessage){
        this.data = null;
        this.errorInfo = ErrorInfo.builder()
                .code(errorCode)
                .message(errorMessage)
                .build();
    }

    @Getter
    public static class ErrorInfo{
        private String code;
        private String message;

        @Builder
        public ErrorInfo(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
