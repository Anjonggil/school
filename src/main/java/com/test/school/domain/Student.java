package com.test.school.domain;

import com.test.school.domain.dto.StudentDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Entity
@Table(name = "STUDENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;


    @NotEmpty
    private String name;

    @NotEmpty
    private int age;


    @Enumerated(EnumType.STRING)
    @NotEmpty
    private SchoolType schoolType;

    @NotEmpty
    private String phoneNumber;

//    @OneToMany(mappedBy = "student")
//    private List<StudentSubject> studentSubjectList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Student(Long id, String name, int age, SchoolType schoolType, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }

    public StudentDto toDto(){
        return StudentDto.builder()
                .name(this.name)
                .age(this.age)
                .schoolType(this.schoolType)
                .phoneNumber(this.phoneNumber)
                .build();
    }

    public boolean validateData() {
        if (name.length() < 1 || name.length() > 16) return false;
        if (age < 8 || age > 19) return false;
        if (!validatePhoneNumber(phoneNumber)) return false;

        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}