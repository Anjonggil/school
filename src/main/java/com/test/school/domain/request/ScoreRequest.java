package com.test.school.domain.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class ScoreRequest {

    private Info info;

    @Getter
    public static class Info {

        @Max(100)
        @Min(0)
        private int score;
    }
}
