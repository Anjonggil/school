package com.test.school.common;

import com.test.school.common.error.ApiException;
import com.test.school.common.error.ApiExceptionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class JsonResultData<T> {

    private T data;
    private ApiExceptionEntity error;

    @Builder(builderClassName = "ApiResultBuilder",builderMethodName = "ApiResultBuilder")
    public JsonResultData(T data, ApiExceptionEntity error){
        this.data = data;
        this.error = error;
    }

}
