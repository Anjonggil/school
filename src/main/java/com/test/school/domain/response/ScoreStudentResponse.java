package com.test.school.domain.response;

import com.test.school.domain.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreStudentResponse {
    private Double averageScore;
    private List<Info> students = new ArrayList<>();

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Info {
        private Long id;
        private String name;
        private int score;
    }

    @Builder(builderMethodName = "of",builderClassName = "of")
    public ScoreStudentResponse(List<Info> students, Double averageScore){
        this.averageScore = averageScore;
        this.students = students;
    }

    @Builder(builderMethodName = "createStudentsResponseBuilder",builderClassName = "createStudentsResponseBuilder")
    public static ScoreStudentResponse createStudentsResponse(List<Score> scoreList,double averageScore){
        List<Info> students = scoreList.stream().map(Score::toStudentInfo).collect(Collectors.toList());
        return ScoreStudentResponse.of()
                .students(students)
                .averageScore(averageScore)
                .build();
    }
}
