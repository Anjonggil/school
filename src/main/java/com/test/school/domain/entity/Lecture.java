package com.test.school.domain.entity;

import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "LECTURE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {
    @Id @GeneratedValue
    @Column(name = "lecture_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "score")
    private Score score;


    @Builder(builderMethodName = "of", builderClassName = "of")
    public Lecture(Long id, Student student, Subject subject) {
        this.id = id;
        this.student = student;
        this.subject = subject;
    }

    @Builder(builderMethodName = "createStudentSubjectBuilder", builderClassName = "createStudentSubjectBuilder")
    public static Lecture createStudentSubject(Student student, Subject subject){
        return Lecture.of()
                .student(student)
                .subject(subject)
                .build();
    }

    public ScoreSubjectResponse.Info toSubjectInfo(){
        return ScoreSubjectResponse.Info.builder()
                .id(subject.getId())
                .name(subject.getName())
                .score(score.getScore())
                .build();
    }

    public ScoreStudentResponse.Info toStudentInfo(){
        return ScoreStudentResponse.Info.builder()
                .id(student.getId())
                .name(student.getName())
                .score(score.getScore())
                .build();
    }
}
