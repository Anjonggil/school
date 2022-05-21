package com.test.school.service.impl;

import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.Score;
import com.test.school.domain.Student;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;
import com.test.school.repository.ScoreRepository;
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
    private final ScoreRepository scoreRepository;

    //학생 조회
    @Override
    public StudentResponse getStudents(){
        List<Student> studentList = studentRepository.findByAll();

        return StudentResponse.createStudentResponse(studentList);
    }

    //학생 등록
    @Transactional
    @Override
    public Long createStudents(StudentRequest.Info studentDto) {
        validateDuplicateStudent(studentDto.getPhoneNumber());

        Student student = studentDto.toEntity();

        studentRepository.save(student);
        return student.getId();
    }

    //학생중복체크
    private void validateDuplicateStudent(String phoneNumber) {
        Student findStudent = studentRepository.findStudentByPhoneNumber(phoneNumber);

        if (findStudent != null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.ALREADY_EXIST_STUDENT.getCode())
                    .errorMessage("이미 존재하는 학생입니다." +"["+findStudent.getPhoneNumber()+"]")
                    .build());
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

        List<Score> scoreList = scoreRepository.findScoresByStudentId(student.getId());
        if (scoreList.size() > 0){
            scoreRepository.deleteAll(scoreList);
        }

        studentRepository.delete(student);
        return true;
    }

    public Student getStudent(Long studentId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null) {
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.STUDENT_NOT_FOUND.getCode())
                    .errorMessage("학생을 찾을 수 없습니다." + " [" + studentId + "]")
                    .build());
        }
        return findStudent;
    }
}
