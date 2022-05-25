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

        @Name(message = "학생 이름은 한글/양어/숫자만을 포함합니다.")
        @Size(min = 1,max = 16 ,message = "학생 이름은 최소 1자 최대 16글자 입니다.")
        private String name;

        @Min(value = 8,message = "학생 나이의 최대값은 8입니다.")
        @Max(value = 19,message = "학생 나이의 최댓값은 19입니다.")
        private int age;

        @Type(message = "학교 등급은 ELEMENTARY, MIDDLE, HIGH 값만 유효합니다.")
        private SchoolType schoolType;

        @Tel(message = "전화번호 형식은 000-0000-0000 입니다.")
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
