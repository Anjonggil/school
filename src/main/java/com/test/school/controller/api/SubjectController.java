package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.entity.Subject;
import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;
import com.test.school.service.LectureService;
import com.test.school.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    private final LectureService lectureService;

    @Operation(summary = "과목 조회 API", description = "과목 조회 API")
    @GetMapping("/subjects")
    public ResponseEntity<JsonResultData<SubjectResponse>> getSubjects(){
        SubjectResponse subjectResponse = subjectService.getSubjects();

        return new ResponseEntity<>(
                JsonResultData.<SubjectResponse>ApiResultBuilder()
                    .data(subjectResponse)
                    .error(null)
                    .build(),
                HttpStatus.OK);
    }

    @Operation(summary = "과목 등록 API", description = "과목 등록 API")
    @PostMapping("/subjects")
    public ResponseEntity<?> createSubjects(
            @Parameter(description = "Subject Request", required = true)
            @RequestBody @Valid SubjectRequest subjectRequest
    ){
        Subject subject = subjectService.createSubjects(subjectRequest.getInfo());
        lectureService.createLectureBySubject(subject);

        return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                .data(null)
                .error(null)
                .build(),HttpStatus.CREATED);
    }

    @Operation(summary = "과목 삭제 API", description = "과목 삭제 API")
    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<?> deleteSubject(
            @Parameter(description = "Subject Id", required = true)
            @PathVariable("subjectId")Long subjectId
    ){
        Boolean isDeleted = subjectService.deleteSubject(subjectId);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }
}
