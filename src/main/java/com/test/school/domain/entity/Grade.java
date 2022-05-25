package com.test.school.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "GRADE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_subject_id")
    private StudentSubject studentSubject;

    private int score;

    public void changeScore(int score) {
        this.score = score;
    }

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Grade(Long id, StudentSubject studentSubject, int score) {
        this.id = id;
        this.studentSubject = studentSubject;
        this.score = score;
    }
}
