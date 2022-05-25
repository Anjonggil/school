package com.test.school.service.impl;

import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.Student;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;
import com.test.school.repository.StudentSubjectRepository;
import com.test.school.repository.StudentRepository;
import com.test.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    //학생 조회
    @Override
    public StudentResponse getStudents(){
        List<Student> studentList = studentRepository.findByAll();

        return StudentResponse.createStudentResponse(studentList);
    }

    //학생 등록
    @Transactional
    @Override
    public Student createStudents(StudentRequest.Info studentDto) {
        validateDuplicateStudent(studentDto.getPhoneNumber());

        Student student = studentDto.toEntity();

        return studentRepository.save(student);
    }

    //학생중복체크
    private void validateDuplicateStudent(String phoneNumber) {
        Student findStudent = studentRepository.findStudentByPhoneNumber(phoneNumber);

        if (findStudent != null){
            StringBuilder sb = new StringBuilder();
            sb.append(ErrorCode.ALREADY_EXIST_STUDENT.getMessage()).append(" [").append(findStudent.getName()).append("]");
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.ALREADY_EXIST_STUDENT.getCode())
                    .errorMessage(sb.toString())
                    .build());
        }
    }

    //학생 삭제
    @Transactional
    @Override
    public Boolean deleteStudent(Long studentId) {
        Student findStudent = studentRepository.findStudentById(studentId);

        if (findStudent == null){
            StringBuilder sb = new StringBuilder();
            sb.append(ErrorCode.STUDENT_NOT_FOUND.getMessage()).append(" [").append(studentId).append("]");
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.STUDENT_NOT_FOUND.getCode())
                    .errorMessage(sb.toString())
                    .build());
        }

        studentRepository.delete(findStudent);
        return true;
    }
}
