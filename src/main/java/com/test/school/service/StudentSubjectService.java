package com.test.school.service;

import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.response.GradeStudentResponse;
import com.test.school.domain.response.GradeSubjectResponse;

public interface StudentSubjectService {

    GradeSubjectResponse getAverageScoreByStudent(Long studentId);

    GradeStudentResponse getAverageScoreBySubject(Long subjectId);

    void createLectureByStudent(Student student);

    void createLectureBySubject(Subject subject);
}
