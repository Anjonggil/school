package com.test.school.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.common.annotation.Name;
import com.test.school.common.annotation.Tel;
import com.test.school.common.annotation.Type;
import com.test.school.domain.SchoolType;
import com.test.school.domain.entity.Student;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
public class StudentRequest {

    @Valid
    @JsonProperty("student")
    private Info info;

    @Getter
    public static class Info {

        @Name
        @Size(min = 1,max = 16)
        private String name;

        @Min(8)
        @Max(19)
        private int age;

        @Type
        private SchoolType schoolType;

        @Tel
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
