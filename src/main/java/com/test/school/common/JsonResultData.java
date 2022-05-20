package com.test.school.common;

import com.test.school.common.error.ApiException;
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
    private int code;

    @Builder(builderClassName = "ApiResultBuilder",builderMethodName = "ApiResultBuilder")
    public JsonResultData(T data, ApiExceptionEntity error, HttpStatus status){
        this.data = data;
        this.error = error;
        this.status = status;
        this.code = status.value();
    }

}
