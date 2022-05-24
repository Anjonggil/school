package com.test.school.service;

import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;

public interface LectureService {
    Long createScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId);

    Lecture updateScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);

    ScoreSubjectResponse getAverageScoreByStudent(Long studentId);

    ScoreStudentResponse getAverageScoreBySubject(Long subjectId);

    void createLectureByStudent(Student student);

    void createLectureBySubject(Subject subject);
}
