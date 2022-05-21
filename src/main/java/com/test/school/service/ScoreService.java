package com.test.school.service;

import com.test.school.domain.Score;
import com.test.school.domain.dto.ScoreDto;

public interface ScoreService {
    Long createScores(ScoreDto.Request scoreDto, Long studentId, Long subjectId);

    Score updateScores(ScoreDto.Request scoreDto, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);
}
