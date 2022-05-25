package com.test.school.service.impl;

import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.StudentSubject;
import com.test.school.domain.entity.Grade;
import com.test.school.domain.request.GradeRequest;
import com.test.school.repository.StudentSubjectRepository;
import com.test.school.repository.GradeRepository;
import com.test.school.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {
    private final StudentSubjectRepository studentSubjectRepository;
    private final GradeRepository gradeRepository;

    @Override
    @Transactional
    public Long createScores(GradeRequest.Info scoreDto, Long studentId, Long subjectId) {
        StudentSubject findStudentSubject = getLecture(studentId, subjectId);

        Grade grade = Grade.of()
                .score(scoreDto.getScore())
                .studentSubject(findStudentSubject)
                .build();

        gradeRepository.save(grade);
        return grade.getId();
    }

    @Override
    @Transactional
    public Grade updateScores(GradeRequest.Info scoreRequest, Long studentId, Long subjectId) {
        StudentSubject findStudentSubject = getLecture(studentId, subjectId);

        Grade findGrade = gradeRepository.findScoreByStudentSubject(findStudentSubject);
        findGrade.changeScore(scoreRequest.getScore());

        return findGrade;
    }

    @Override
    public Boolean deleteScore(Long studentId, Long subjectId) {
        StudentSubject findStudentSubject = getLecture(studentId,subjectId);
        Grade findGrade = gradeRepository.findScoreByStudentSubject(findStudentSubject);
        if (findGrade != null) {
            gradeRepository.delete(findGrade);
            return true;
        }else{
            return false;
        }
    }

    public StudentSubject getLecture(Long studentId, Long subjectId){
        StudentSubject findStudentSubject = studentSubjectRepository.findLectureByStudentIdAndSubjectId(studentId, subjectId);
        if (findStudentSubject.getStudent() == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.STUDENT_NOT_FOUND.getCode())
                    .errorMessage("학생을 찾을 수 없습니다." + " [" + studentId + "]")
                    .build());
        }

        if (findStudentSubject.getSubject() == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.SUBJECT_NOT_FOUND.getCode())
                    .errorMessage("과목을 찾을 수 없습니다." + " [" + subjectId + "]")
                    .build());
        }

        return findStudentSubject;
    }
}
