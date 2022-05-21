package com.test.school.domain.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ScoreDto {

    private Request request;

    @Getter
    public static class Request{
        private int score;
    }

    @Getter
    public static class Response{
        private Double averageScore;
    }
}
