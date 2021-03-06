package com.test.school.domain.entity;

import com.test.school.domain.response.SubjectResponse;
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

    @Column(name = "subject_name",length = 50)
    private String name;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<StudentSubject> studentSubjectList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectResponse.Info toDto(){
        return SubjectResponse.Info.of()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
