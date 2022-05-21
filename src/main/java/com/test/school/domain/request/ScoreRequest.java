package com.test.school.domain.request;

import lombok.Getter;

@Getter
public class ScoreRequest {

    private Info info;

    @Getter
    public static class Info {
        private int score;
    }
}
