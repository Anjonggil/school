package com.test.school.domain.request;

import com.test.school.domain.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreSubjectResponse {
    private Double averageScore;
    private List<Info> subjects;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Info {
        private Long id;
        private String name;
        private int score;
    }

    @Builder(builderMethodName = "of",builderClassName = "of")
    public ScoreSubjectResponse(Double averageScore, List<Info> subjects) {
        this.averageScore = averageScore;
        this.subjects = subjects;
    }

    @Builder(builderMethodName = "createSubjectsResponseBuilder",builderClassName = "createSubjectsResponseBuilder")
    public static ScoreSubjectResponse createSubjectsResponse(List<Score> scoreList){
        double averageScore = -1d;
        averageScore = getAverageScore(scoreList, averageScore);
        List<Info> subjects = scoreList.stream().map(Score::toSubjectInfo).collect(Collectors.toList());
        return ScoreSubjectResponse.of()
                .averageScore(averageScore)
                .subjects(subjects)
                .build();
    }

    private static double getAverageScore(List<Score> scoreList, double averageScore) {
        if (scoreList.size() > 0){
            averageScore = scoreList.stream().mapToDouble(Score::getScore).average().orElse(0.0);
        }
        return averageScore;
    }
}
