package com.test.school.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "STUDENT_SUBJECT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentSubject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int score;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public StudentSubject(Long id, Student student, Subject subject, int score) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.score = score;
    }
}
