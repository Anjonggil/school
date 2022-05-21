package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.dto.SubjectDto;
import com.test.school.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public JsonResultData<Map<String,List<SubjectDto.Response>>> getSubjects(){
        List<SubjectDto.Response> subjectList = subjectService.getSubjects();

        Map<String,List<SubjectDto.Response>> result = new HashMap<>();
        if (subjectList.size() > 0){
            result.put("subjects",subjectList);
        }else{
            result = null;
        }

        return JsonResultData.<Map<String,List<SubjectDto.Response>>>ApiResultBuilder()
                .data(result)
                .error(null)
                .build();
    }

    @PostMapping("/subjects")
    public JsonResultData<?> createSubjects(@RequestBody SubjectDto subjectDto){
        Long id = subjectService.createSubjects(subjectDto.getRequest());
        if (id != null){
            return JsonResultData.ApiResultBuilder()
                    .data(null)
                    .error(null)
                    .build();
        }else{
            return JsonResultData.ApiResultBuilder()
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
                    .build();
        }else{
            return JsonResultData.ApiResultBuilder()
                    .build();
        }
    }
}
