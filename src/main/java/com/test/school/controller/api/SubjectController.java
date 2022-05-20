package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.domain.dto.SubjectDto;
import com.test.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public JsonResultData<List<SubjectDto>> getSubjects(){
        List<SubjectDto> subjectList = subjectService.getSubjects();

        return JsonResultData.<List<SubjectDto>>ApiResultBuilder()
                .data(subjectList)
                .error(null)
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/subjects")
    public JsonResultData<?> setSubject(@RequestBody SubjectDto subjectDto){
        Long id = subjectService.setSubject(subjectDto);
        if (id != null){
            return JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .status(HttpStatus.CREATED)
                    .build();
        }else{
            return JsonResultData.ApiResultBuilder()
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    @DeleteMapping("/subjects/{subjectId}")
    public JsonResultData<?> deleteSubject(
            @PathVariable("subjectId")Long subjectId
    ){
        Boolean isDeleted = subjectService.deleteSubject(subjectId);
        if (isDeleted){
            return JsonResultData.ApiResultBuilder()
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }else{
            return JsonResultData.ApiResultBuilder()
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
