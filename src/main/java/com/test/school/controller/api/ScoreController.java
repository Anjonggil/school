package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Score;
import com.test.school.domain.request.ScoreRequest;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.response.ScoreSubjectResponse;
import com.test.school.service.LectureService;
import com.test.school.service.ScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScoreController {
    private final LectureService lectureService;
    private final ScoreService scoreService;

    @Operation(summary = "점수 등록 API", description = "점수 등록 API")
    @PostMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> createScores(
            @Parameter(description = "Student Id", required = true) @PathVariable("studentId") Long studentId,
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId,
            @Parameter(description = "Score Request", required = true) @RequestBody @Valid ScoreRequest.Info scoreRequest){

        Long id = scoreService.createScores(scoreRequest, studentId,subjectId);
        if (id != null){
            return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .build(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "점수 수정 API", description = "점수 수정 API")
    @PutMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> updateScores(
            @Parameter(description = "Student Id", required = true) @PathVariable("studentId") Long studentId,
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId,
            @Parameter(description = "Score Request", required = true) @RequestBody @Valid ScoreRequest.Info scoreRequest){
        Score score = scoreService.updateScores(scoreRequest, studentId, subjectId);
        if (score != null && score.getScore() == scoreRequest.getScore()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "점수 삭제 API", description = "점수 삭제 API")
    @DeleteMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> deleteScores(
            @Parameter(description = "Student Id", required = true) @PathVariable("studentId") Long studentId,
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId){
        Boolean isDelete = scoreService.deleteScore(studentId,subjectId);
        if (isDelete){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "회원 평균 점수 API", description = "회원 평균 점수 API")
    @GetMapping("/students/{studentId}/average-score")
    public ResponseEntity<?> getAverageScoreByStudent(
            @Parameter(description = "Student Id", required = true) @PathVariable("studentId") Long studentId){
        ScoreSubjectResponse scoreSubjectResponse = lectureService.getAverageScoreByStudent(studentId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(scoreSubjectResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }

    @Operation(summary = "과목 평균 점수 API", description = "과목 평균 점수 API")
    @GetMapping("/subjects/{subjectId}/average-score")
    public ResponseEntity<?> getAverageScoreBySubject(
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId){
        ScoreStudentResponse scoreStudentResponse = lectureService.getAverageScoreBySubject(subjectId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(scoreStudentResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }
}
