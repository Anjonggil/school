package com.test.school.service;

import com.test.school.domain.entity.Grade;
import com.test.school.domain.request.GradeRequest;

public interface GradeService {
    Long createScores(GradeRequest.Info scoreRequest, Long studentId, Long subjectId);

    Grade updateScores(GradeRequest.Info scoreRequest, Long studentId, Long subjectId);

    Boolean deleteScore(Long studentId, Long subjectId);
}
