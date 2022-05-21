package com.test.school.domain;

import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;
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
    @Column(name = "score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private int score;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public Score(Long id, Student student, Subject subject, int score) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.score = score;
    }

    @Builder(builderMethodName = "createStudentSubjectBuilder", builderClassName = "createStudentSubjectBuilder")
    public static Score createStudentSubject(ScoreRequest.Info scoreDto, Student student, Subject subject){
        return Score.of()
                .score(scoreDto.getScore())
                .student(student)
                .subject(subject)
                .build();
    }

    public void changeScore(ScoreRequest.Info scoreDto) {
        this.score = scoreDto.getScore();
    }

    public ScoreSubjectResponse.Info toSubjectInfo(){
        return ScoreSubjectResponse.Info.builder()
                .id(subject.getId())
                .name(subject.getName())
                .score(score)
                .build();
    }

    public ScoreStudentResponse.Info toStudentInfo(){
        return ScoreStudentResponse.Info.builder()
                .id(student.getId())
                .name(student.getName())
                .score(score)
                .build();
    }
}
