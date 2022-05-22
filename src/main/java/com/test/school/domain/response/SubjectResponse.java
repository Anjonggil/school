package com.test.school.domain.response;

import com.test.school.domain.entity.Subject;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubjectResponse {

    private List<Info> subjects = new ArrayList<>();

    @Getter
    public static class Info {
        private Long id;
        private String name;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Info(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }


    @Builder(builderMethodName = "of",builderClassName = "of")
    public SubjectResponse(List<SubjectResponse.Info> subjects) {
        this.subjects = subjects;
    }

    public static SubjectResponse createStudentResponse(List<Subject> subjects){
        return SubjectResponse.of()
                .subjects(subjects.stream()
                        .map(Subject::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
