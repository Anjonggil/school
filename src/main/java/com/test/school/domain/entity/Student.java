package com.test.school.domain.entity;

import com.test.school.domain.SchoolType;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Entity
@Table(name = "STUDENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name",length = 50)
    private String name;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "school_type",length = 10)
    private SchoolType schoolType;

    @Column(name = "phone_number",length = 15)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    private List<Score> scoreList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Student(Long id, String name, int age, SchoolType schoolType, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.schoolType = schoolType;
        this.phoneNumber = phoneNumber;
    }

    public StudentResponse.Info toDto(){
        return StudentResponse.Info.of()
                .name(this.name)
                .age(this.age)
                .schoolType(this.schoolType)
                .phoneNumber(this.phoneNumber)
                .build();
    }

    public double getAverage() {
        double averageScore = -1d;
        if (this.scoreList.size() > 0) averageScore = this.scoreList.stream().mapToDouble(Score::getScore).average().orElse(0.0);
        return averageScore;
    }
}
