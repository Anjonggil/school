package com.test.school.domain.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class GradeRequest {

    private Info info;

    @Getter
    public static class Info {

        @Max(value = 100, message = "점수는 100 이하의 숫자여야만 합니다.")
        @Min(value = 0, message = "점수는 0 이상의 숫자여야만 합니다.")
        private int score;
    }
}
