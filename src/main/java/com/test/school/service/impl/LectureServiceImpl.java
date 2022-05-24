package com.test.school.service.impl;

import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Score;
import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;
import com.test.school.repository.LectureRepository;
import com.test.school.repository.StudentRepository;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Override
    public ScoreSubjectResponse getAverageScoreByStudent(Long studentId) {
        Student findStudent = getStudent(studentId);
        List<Score> scoreList = findStudent.getLectureList().stream().map(Lecture::getScore).collect(Collectors.toList());
        double averageScore = getAverage(scoreList);

        return ScoreSubjectResponse.createSubjectsResponseBuilder()
                .lectureList(findStudent.getLectureList())
                .averageScore(averageScore)
                .build();
    }

    @Override
    public ScoreStudentResponse getAverageScoreBySubject(Long subjectId) {
        Subject findSubject = getSubject(subjectId);
        List<Score> scoreList = findSubject.getLectureList().stream().map(Lecture::getScore).collect(Collectors.toList());
        double averageScore = getAverage(scoreList);

        return ScoreStudentResponse.createStudentsResponseBuilder()
                .lectureList(findSubject.getLectureList())
                .averageScore(averageScore)
                .build();
    }

    @Override
    @Transactional
    public void createLectureByStudent(Student student) {
        List<Subject> subjectList = subjectRepository.findByAll();
        if (subjectList.size() > 0){
            for (Subject subject : subjectList) {
                Lecture lecture = Lecture.createStudentSubjectBuilder()
                        .student(student)
                        .subject(subject)
                        .build();

                lectureRepository.save(lecture);
            }
        }
    }

    @Override
    @Transactional
    public void createLectureBySubject(Subject subject) {
        List<Student> studentList = studentRepository.findByAll();
        if (studentList.size() > 0){
            for (Student student : studentList) {
                Lecture lecture = Lecture.createStudentSubjectBuilder()
                        .student(student)
                        .subject(subject)
                        .build();

                lectureRepository.save(lecture);
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

    public double getAverage(List<Score> scoreList) {
        double averageScore = -1d;
        if (scoreList.size() > 0) averageScore = scoreList.stream().mapToDouble(Score::getScore).average().orElse(0.0);
        return averageScore;
    }
}
