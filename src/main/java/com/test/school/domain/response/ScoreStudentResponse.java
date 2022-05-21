package com.test.school.domain.response;

import com.test.school.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreStudentResponse {
    private Double averageScore;
    private List<Info> students;

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
    public static ScoreStudentResponse createStudentsResponse(List<Score> scoreList){
        double averageScore = -1d;
        averageScore = getAverageScore(scoreList, averageScore);
        List<Info> students = scoreList.stream().map(Score::toStudentInfo).collect(Collectors.toList());
        return ScoreStudentResponse.of()
                .students(students)
                .averageScore(averageScore)
                .build();
    }

    private static double getAverageScore(List<Score> scoreList, double averageScore) {
        if (scoreList.size() > 0){
            averageScore = scoreList.stream().mapToDouble(Score::getScore).average().orElse(0.0);
        }
        return averageScore;
    }
}
