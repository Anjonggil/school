package com.test.school.domain.dto;

import com.test.school.domain.SchoolType;
import com.test.school.domain.Student;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.web.JsonPath;

import java.util.List;

@Getter
public class StudentDto {
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

    @Builder
    public StudentDto(String name, int age, SchoolType schoolType, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }
}
