package com.test.school.service;

import com.test.school.domain.Student;
import com.test.school.domain.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> getStudents();
    Long setStudent(StudentDto studentDto);
    Boolean deleteStudent(Long id);
}
