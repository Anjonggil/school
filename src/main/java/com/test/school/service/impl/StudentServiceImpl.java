package com.test.school.service.impl;

import com.test.school.common.error.ApiException;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.ExceptionEnum;
import com.test.school.domain.Student;
import com.test.school.domain.dto.StudentDto;
import com.test.school.repository.StudentRepository;
import com.test.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    //학생 조회
    @Override
    public List<StudentDto.Response> getStudents(){
        List<Student> studentList = studentRepository.findByAll();

        return studentList.stream().map(Student::toDto).collect(Collectors.toList());
    }

    //학생 등록
    @Transactional
    @Override
    public Long createStudents(StudentDto.Request studentDto) {
        validateDuplicateStudent(studentDto.getPhoneNumber());

        Student student = studentDto.toEntity();

        if (!student.validateData()){
            throw new ApiException(ExceptionEnum.BAD_REQUEST_BODY);
        }

        studentRepository.save(student);
        return student.getId();
    }

    //학생중복체크
    private void validateDuplicateStudent(String phoneNumber) {
        Student findStudent = studentRepository.findStudentByPhoneNumber(phoneNumber);

        if (findStudent != null){
            throw new ApiException(new ApiExceptionEntity("ALREADY_EXIST_STUDENT", "이미 존재하는 학생입니다." +"["+findStudent.getPhoneNumber()+"]" ));
        }
    }

    //학생 삭제
    @Transactional
    @Override
    public Boolean deleteStudent(Long id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null){
            return false;
        }

        studentRepository.delete(student);
        return true;
    }
}
