package com.test.school.domain.response;

import com.test.school.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScoreSubjectResponse {
    private Double averageScore;
    private List<Info> subjects = new ArrayList<>();

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
    public static ScoreSubjectResponse createSubjectsResponse(List<Lecture> lectureList, double averageScore){
        List<Info> subjects = lectureList.stream().map(Lecture::toSubjectInfo).collect(Collectors.toList());
        return ScoreSubjectResponse.of()
                .averageScore(averageScore)
                .subjects(subjects)
                .build();
    }
}
