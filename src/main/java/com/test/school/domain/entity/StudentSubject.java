package com.test.school.domain.entity;

import com.test.school.domain.response.GradeStudentResponse;
import com.test.school.domain.response.GradeSubjectResponse;
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

    @OneToOne(mappedBy = "studentSubject",cascade = CascadeType.ALL)
    private Grade grade;


    @Builder(builderMethodName = "of", builderClassName = "of")
    public StudentSubject(Long id, Student student, Subject subject) {
        this.id = id;
        this.student = student;
        this.subject = subject;
    }

    @Builder(builderMethodName = "createStudentSubjectBuilder", builderClassName = "createStudentSubjectBuilder")
    public static StudentSubject createStudentSubject(Student student, Subject subject){
        return StudentSubject.of()
                .student(student)
                .subject(subject)
                .build();
    }

    public GradeSubjectResponse.Info toSubjectInfo(){
        return GradeSubjectResponse.Info.builder()
                .id(subject.getId())
                .name(subject.getName())
                .score(grade.getScore())
                .build();
    }

    public GradeStudentResponse.Info toStudentInfo(){
        return GradeStudentResponse.Info.builder()
                .id(student.getId())
                .name(student.getName())
                .score(grade.getScore())
                .build();
    }
}
