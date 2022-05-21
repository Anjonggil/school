package com.test.school.common.error;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({BadRequestApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final BadRequestApiException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getErrorEntity().getErrorInfo().getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({NotFoundApiException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final NotFoundApiException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiExceptionEntity.builder()
                        .errorCode(e.getErrorEntity().getErrorInfo().getCode())
                        .errorMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final MethodArgumentNotValidException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionEntity.builder()
                        .errorCode(ErrorCode.BAD_REQUEST_BODY.getCode())
                        .errorMessage("파라미터 오류")
                        .build());
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request, final InvalidFormatException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiExceptionEntity.builder()
                        .errorCode(ErrorCode.BAD_REQUEST_BODY.getCode())
                        .errorMessage("파라미터 오류")
                        .build());
    }
}
