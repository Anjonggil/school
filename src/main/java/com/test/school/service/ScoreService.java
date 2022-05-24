package com.test.school.service;

import com.test.school.domain.entity.Score;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreSubjectResponse;

public interface ScoreService {
    Long createScores(ScoreRequest.Info scoreRequest, Long studentId, Long subjectId);

    Score updateScores(ScoreRequest.Info scoreRequest, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);
}
