package com.test.school.domain.response;

import com.test.school.domain.SchoolType;
import com.test.school.domain.entity.Student;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentResponse {

    private List<Info> students = new ArrayList<>();

    @Getter
    public static class Info {
        private String name;
        private int age;
        private SchoolType schoolType;
        private String phoneNumber;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Info(String name, int age, SchoolType schoolType, String phoneNumber) {
            this.name = name;
            this.age = age;
            this.schoolType = schoolType;
            this.phoneNumber = phoneNumber;
        }
    }

    @Builder(builderMethodName = "of",builderClassName = "of")
    public StudentResponse(List<Info> students) {
        this.students = students;
    }

    public static StudentResponse createStudentResponse(List<Student> students){
        return StudentResponse.of()
                .students(students.stream()
                                .map(Student::toDto)
                                .collect(Collectors.toList()))
                .build();
    }
}
