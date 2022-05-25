package com.test.school.domain.response;

import com.test.school.domain.entity.StudentSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class GradeSubjectResponse {
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
    public GradeSubjectResponse(Double averageScore, List<Info> subjects) {
        this.averageScore = averageScore;
        this.subjects = subjects;
    }

    @Builder(builderMethodName = "createSubjectsResponseBuilder",builderClassName = "createSubjectsResponseBuilder")
    public static GradeSubjectResponse createSubjectsResponse(List<StudentSubject> studentSubjectList, double averageScore){
        List<Info> subjects = new ArrayList<>();
        subjects = studentSubjectList.stream().map(studentSubject -> {
            if (studentSubject.getGrade()!= null) {
                return studentSubject.toSubjectInfo();
            }else{
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return GradeSubjectResponse.of()
                .averageScore(averageScore)
                .subjects(subjects)
                .build();
    }
}
