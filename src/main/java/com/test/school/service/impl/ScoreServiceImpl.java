package com.test.school.service.impl;

import com.test.school.domain.Student;
import com.test.school.domain.Score;
import com.test.school.domain.Subject;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;
import com.test.school.repository.ScoreRepository;
import com.test.school.repository.StudentRepository;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public Long createScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null){
            //TODO : 에러처리
        }
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null){
            //TODO : 에러처리
        }

        Score score = Score.createStudentSubjectBuilder()
                .scoreDto(scoreDto)
                .student(findStudent)
                .subject(findSubject)
                .build();

        scoreRepository.save(score);
        return score.getId();
    }

    @Override
    @Transactional
    public Score updateScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null){
            //TODO : 에러처리
        }
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null){
            //TODO : 에러처리
        }

        Score findScore = scoreRepository.findScoreByStudentAndSubject(findStudent, findSubject);
        if (findScore != null) findScore.changeScore(scoreDto);

        return findScore;
    }

    @Override
    public Boolean deleteScore(Long studentId, Long subjectId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null){
            //TODO : 에러처리
        }
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null){
            //TODO : 에러처리
        }
        Score findScore = scoreRepository.findScoreByStudentAndSubject(findStudent, findSubject);
        if (findScore != null) {
            scoreRepository.delete(findScore);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ScoreSubjectResponse getAverageScoreByStudent(Long studentId) {
        Student findStudent = studentRepository.findStudentById(studentId);
        if (findStudent == null){
            //TODO : 에러처리
        }

        List<Score> scoreList = scoreRepository.findScoresByStudentId(findStudent.getId());
        return ScoreSubjectResponse.createSubjectsResponse(scoreList);
    }

    @Override
    public ScoreStudentResponse getAverageScoreBySubject(Long subjectId) {
        Subject findSubject = subjectRepository.findSubjectById(subjectId);
        if (findSubject == null){
            //TODO : 에러처리
        }

        List<Score> scoreList = scoreRepository.findScoresBySubjectId(findSubject.getId());
        return ScoreStudentResponse.createStudentsResponse(scoreList);
    }
}
