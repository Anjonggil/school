package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.entity.Grade;
import com.test.school.domain.request.GradeRequest;
import com.test.school.domain.response.GradeStudentResponse;
import com.test.school.domain.response.GradeSubjectResponse;
import com.test.school.service.StudentSubjectService;
import com.test.school.service.GradeService;
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
public class GradeController {
    private final StudentSubjectService studentSubjectService;
    private final GradeService gradeService;

    @Operation(summary = "점수 등록 API", description = "점수 등록 API")
    @PostMapping("/students/{studentId}/subjects/{subjectId}/scores")
    public ResponseEntity<?> createScores(
            @Parameter(description = "Student Id", required = true) @PathVariable("studentId") Long studentId,
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId,
            @Parameter(description = "Score Request", required = true) @RequestBody @Valid GradeRequest.Info scoreRequest){

        Long id = gradeService.createScores(scoreRequest, studentId,subjectId);
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
            @Parameter(description = "Score Request", required = true) @RequestBody @Valid GradeRequest.Info scoreRequest){
        Grade grade = gradeService.updateScores(scoreRequest, studentId, subjectId);
        if (grade != null && grade.getScore() == scoreRequest.getScore()){
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
        Boolean isDelete = gradeService.deleteScore(studentId,subjectId);
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
        GradeSubjectResponse gradeSubjectResponse = studentSubjectService.getAverageScoreByStudent(studentId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(gradeSubjectResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }

    @Operation(summary = "과목 평균 점수 API", description = "과목 평균 점수 API")
    @GetMapping("/subjects/{subjectId}/average-score")
    public ResponseEntity<?> getAverageScoreBySubject(
            @Parameter(description = "Subject Id", required = true) @PathVariable("subjectId") Long subjectId){
        GradeStudentResponse gradeStudentResponse = studentSubjectService.getAverageScoreBySubject(subjectId);
        return new ResponseEntity<>(
                JsonResultData.ApiResultBuilder()
                        .data(gradeStudentResponse)
                        .error(null)
                        .build(),
                HttpStatus.OK);
    }
}
