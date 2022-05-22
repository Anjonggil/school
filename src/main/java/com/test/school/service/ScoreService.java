package com.test.school.service;

import com.test.school.domain.entity.Score;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;

public interface ScoreService {
    Long createScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId);

    Score updateScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);

    ScoreSubjectResponse getAverageScoreByStudent(Long studentId);

    ScoreStudentResponse getAverageScoreBySubject(Long subjectId);
}
