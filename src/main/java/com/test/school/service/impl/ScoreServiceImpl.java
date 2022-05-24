package com.test.school.service.impl;

import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.common.error.BadRequestApiException;
import com.test.school.common.error.ErrorCode;
import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Score;
import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreSubjectResponse;
import com.test.school.repository.LectureRepository;
import com.test.school.repository.ScoreRepository;
import com.test.school.repository.StudentRepository;
import com.test.school.repository.SubjectRepository;
import com.test.school.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScoreServiceImpl implements ScoreService {
    private final LectureRepository lectureRepository;
    private final ScoreRepository scoreRepository;

    @Override
    @Transactional
    public Long createScores(ScoreRequest.Info scoreDto, Long studentId, Long subjectId) {
        Lecture findLecture = getLecture(studentId, subjectId);

        Score score = Score.of()
                .score(scoreDto.getScore())
                .lecture(findLecture)
                .build();

        scoreRepository.save(score);
        return score.getId();
    }

    @Override
    @Transactional
    public Score updateScores(ScoreRequest.Info scoreRequest, Long studentId, Long subjectId) {
        Lecture findLecture = getLecture(studentId, subjectId);

        Score findScore = scoreRepository.findScoreByLecture(findLecture);
        findScore.changeScore(scoreRequest.getScore());

        return findScore;
    }

    @Override
    public Boolean deleteScore(Long studentId, Long subjectId) {
        Lecture findLecture = getLecture(studentId,subjectId);
        Score findScore = scoreRepository.findScoreByLecture(findLecture);
        if (findScore != null) {
            scoreRepository.delete(findScore);
            return true;
        }else{
            return false;
        }
    }

    public Lecture getLecture(Long studentId,Long subjectId){
        Lecture findLecture = lectureRepository.findLectureByStudentIdAndSubjectId(studentId, subjectId);
        if (findLecture.getStudent() == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.STUDENT_NOT_FOUND.getCode())
                    .errorMessage("학생을 찾을 수 없습니다." + " [" + studentId + "]")
                    .build());
        }

        if (findLecture.getSubject() == null){
            throw new BadRequestApiException(ApiExceptionEntity.builder()
                    .errorCode(ErrorCode.SUBJECT_NOT_FOUND.getCode())
                    .errorMessage("과목을 찾을 수 없습니다." + " [" + subjectId + "]")
                    .build());
        }

        return findLecture;
    }
}
