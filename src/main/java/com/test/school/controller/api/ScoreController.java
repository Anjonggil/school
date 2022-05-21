package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.Score;
import com.test.school.domain.dto.ScoreDto;
import com.test.school.domain.response.ScoreStudentResponse;
import com.test.school.domain.request.ScoreSubjectResponse;
import com.test.school.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreService scoreService;

    @PostMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> createScores(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId,
            @RequestBody ScoreDto.Request scoreDto){

        Long id = scoreService.createScores(scoreDto, studentId, subjectId);
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

    @PutMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> updateScores(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId,
            @RequestBody ScoreDto.Request scoreDto){
        Score score = scoreService.updateScores(scoreDto,studentId,subjectId);
        if (score != null && score.getScore() == scoreDto.getScore()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> deleteScores(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId){
        Boolean isDelete = scoreService.deleteScore(studentId,subjectId);
        if (isDelete){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/students/{studentId}/average-score")
    public ResponseEntity<?> getAverageScoreByStudent(
            @PathVariable("studentId") Long studentId){
        ScoreSubjectResponse scoreSubjectResponse = scoreService.getAverageScoreByStudent(studentId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(scoreSubjectResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/subjects/{subjectId}/average-score")
    public ResponseEntity<?> getAverageScoreBySubject(
            @PathVariable("subjectId") Long subjectId){
        ScoreStudentResponse scoreSubjectResponse = scoreService.getAverageScoreBySubject(subjectId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(scoreSubjectResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }
}
