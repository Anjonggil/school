package com.test.school.service.impl;

import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.StudentSubject;
import com.test.school.domain.entity.Grade;
import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.response.GradeStudentResponse;
import com.test.school.domain.response.GradeSubjectResponse;
import com.test.school.repository.StudentSubjectRepository;
import com.test.school.repository.StudentRepository;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.StudentSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentSubjectServiceImpl implements StudentSubjectService {
    private final StudentSubjectRepository studentSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Override
    public GradeSubjectResponse getAverageScoreByStudent(Long studentId) {
        Student findStudent = getStudent(studentId);
        List<Grade> gradeList = findStudent.getStudentSubjectList()
                .stream().map(StudentSubject::getGrade).filter(Objects::nonNull).collect(Collectors.toList());
        double averageScore = getAverage(gradeList);

        return GradeSubjectResponse.createSubjectsResponseBuilder()
                .studentSubjectList(findStudent.getStudentSubjectList())
                .averageScore(averageScore)
                .build();
    }

    @Override
    public GradeStudentResponse getAverageScoreBySubject(Long subjectId) {
        Subject findSubject = getSubject(subjectId);
        List<Grade> gradeList = findSubject.getStudentSubjectList()
                .stream().map(StudentSubject::getGrade).filter(Objects::nonNull).collect(Collectors.toList());
        double averageScore = getAverage(gradeList);

        return GradeStudentResponse.createStudentsResponseBuilder()
                .studentSubjectList(findSubject.getStudentSubjectList())
                .averageScore(averageScore)
                .build();
    }

    @Override
    @Transactional
    public void createLectureByStudent(Student student) {
        List<Subject> subjectList = subjectRepository.findByAll();
        if (subjectList.size() > 0){
            for (Subject subject : subjectList) {
                StudentSubject studentSubject = StudentSubject.createStudentSubjectBuilder()
                        .student(student)
                        .subject(subject)
                        .build();

                studentSubjectRepository.save(studentSubject);
            }
        }
    }

    @Override
    @Transactional
    public void createLectureBySubject(Subject subject) {
        List<Student> studentList = studentRepository.findByAll();
        if (studentList.size() > 0){
            for (Student student : studentList) {
                StudentSubject studentSubject = StudentSubject.createStudentSubjectBuilder()
                        .student(student)
                        .subject(subject)
                        .build();

                studentSubjectRepository.save(studentSubject);
            }
        }
    }

    public Student getStudent(Long studentId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.STUDENT_NOT_FOUND.getCode())
                    .errorMessage("학생을 찾을 수 없습니다." + " [" + studentId + "]")
                    .build());
        }
        return findStudent;
    }

    public Subject getSubject(Long subjectId) {
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.SUBJECT_NOT_FOUND.getCode())
                    .errorMessage("과목을 찾을 수 없습니다." + " [" + subjectId + "]")
                    .build());
        }
        return findSubject;
    }

    public double getAverage(List<Grade> gradeList) {
        double averageScore = -1d;
        if (gradeList.size() > 0) averageScore = gradeList.stream().mapToDouble(Grade::getScore).average().orElse(0.0);
        return averageScore;
    }
}
