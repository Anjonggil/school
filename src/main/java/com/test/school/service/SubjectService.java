package com.test.school.service;

import com.test.school.domain.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto.Response> getSubjects();
    Long createSubjects(SubjectDto.Request subjectDto);
    Boolean deleteSubject(Long subjectId);
}
