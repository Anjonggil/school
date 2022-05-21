package com.test.school.service;

import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse getStudents();

    Long createStudents(StudentRequest.Info studentDto);

    Boolean deleteStudent(Long id);
}
