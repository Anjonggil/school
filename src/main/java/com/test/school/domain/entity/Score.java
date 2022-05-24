package com.test.school.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SCORE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Score {
    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_subject_id")
    private Lecture lecture;

    private int score;

    public void changeScore(int score) {
        this.score = score;
    }
}
