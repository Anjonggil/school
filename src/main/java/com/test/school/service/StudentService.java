package com.test.school.service;

import com.test.school.domain.entity.Student;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;

public interface StudentService {
    StudentResponse getStudents();

    Student createStudents(StudentRequest.Info studentDto);

    Boolean deleteStudent(Long id);
}
