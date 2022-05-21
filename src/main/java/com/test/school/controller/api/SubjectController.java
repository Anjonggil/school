package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;
import com.test.school.service.SubjectService;
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

    @PostMapping("/subjects")
    public ResponseEntity<?> createSubjects(@RequestBody @Valid SubjectRequest subjectRequest){
        Long id = subjectService.createSubjects(subjectRequest.getInfo());
        if (id != null){
            return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .build(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<?> deleteSubject(
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
