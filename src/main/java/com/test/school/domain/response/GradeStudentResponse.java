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
public class GradeStudentResponse {
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
    public GradeStudentResponse(List<Info> students, Double averageScore){
        this.averageScore = averageScore;
        this.students = students;
    }

    @Builder(builderMethodName = "createStudentsResponseBuilder",builderClassName = "createStudentsResponseBuilder")
    public static GradeStudentResponse createStudentsResponse(List<StudentSubject> studentSubjectList, double averageScore){
        List<Info> students = new ArrayList<>();
        if (averageScore != -1) students = studentSubjectList.stream().map(studentSubject -> {
            if (studentSubject.getGrade()!= null) {
                return studentSubject.toStudentInfo();
            }else{
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return GradeStudentResponse.of()
                .students(students)
                .averageScore(averageScore)
                .build();
    }
}
