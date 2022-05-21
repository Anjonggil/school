package com.test.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.domain.SchoolType;
import com.test.school.domain.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudentDto {

    @JsonProperty("student")
    private Request request;

    @Getter
    public static class Request{
        private String name;
        private int age;
        private SchoolType schoolType;
        private String phoneNumber;

        public Student toEntity(){
            return Student.of()
                    .name(this.name)
                    .age(this.age)
                    .schoolType(this.schoolType)
                    .phoneNumber(this.phoneNumber)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private String name;
        private int age;
        private SchoolType schoolType;
        private String phoneNumber;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Response(String name, int age, SchoolType schoolType, String phoneNumber) {
            this.name = name;
            this.age = age;
            this.schoolType = schoolType;
            this.phoneNumber = phoneNumber;
        }
    }
}
