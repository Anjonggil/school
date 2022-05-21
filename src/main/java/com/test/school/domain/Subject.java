package com.test.school.domain;

import com.test.school.domain.dto.SubjectDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SUBJECT")
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "subject")
//    private List<StudentSubject> studentSubjectList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectDto.Response toDto(){
        return SubjectDto.Response.of()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public boolean validateData() {
        return name.length() >= 1 && name.length() <= 16;
    }
}
