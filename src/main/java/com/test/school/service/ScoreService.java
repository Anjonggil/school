package com.test.school.service;

import com.test.school.domain.Score;
import com.test.school.domain.dto.ScoreDto;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.request.ScoreSubjectResponse;

public interface ScoreService {
    Long createScores(ScoreDto.Request scoreDto, Long studentId, Long subjectId);

    Score updateScores(ScoreDto.Request scoreDto, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);

    ScoreSubjectResponse getAverageScoreByStudent(Long studentId);

    ScoreStudentResponse getAverageScoreBySubject(Long subjectId);
}
