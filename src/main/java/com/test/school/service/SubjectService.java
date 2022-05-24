package com.test.school.service;

import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    SubjectResponse getSubjects();

    Subject createSubjects(SubjectRequest.Info subjectDto);

    Boolean deleteSubject(Long subjectId);
}
