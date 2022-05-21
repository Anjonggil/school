package com.test.school.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.domain.SchoolType;
import com.test.school.domain.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StudentRequest {

    @JsonProperty("student")
    private Info info;

    @Getter
    public static class Info {
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
}
