package com.test.school.common;

import com.test.school.common.error.ApiExceptionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class JsonResultData<T> {

    private T data;
    private ApiExceptionEntity error;
    private HttpStatus status;
}
