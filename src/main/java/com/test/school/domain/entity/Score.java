package com.test.school.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SCORE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_subject_id")
    private Lecture lecture;

    private int score;

    public void changeScore(int score) {
        this.score = score;
    }

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Score(Long id, Lecture lecture, int score) {
        this.id = id;
        this.lecture = lecture;
        this.score = score;
    }
}
