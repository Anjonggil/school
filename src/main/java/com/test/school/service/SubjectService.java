package com.test.school.service;

import com.test.school.domain.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getSubjects();
    Long setSubject(SubjectDto subjectDto);

    Boolean deleteSubject(Long subjectId);
}
