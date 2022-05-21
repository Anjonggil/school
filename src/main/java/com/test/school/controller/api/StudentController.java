package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.dto.StudentDto;
import com.test.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public JsonResultData<Map<String,List<StudentDto.Response>>> getStudents(){
        List<StudentDto.Response> studentDtoList = studentService.getStudents();

        Map<String,List<StudentDto.Response>> result = new HashMap<>();
        if (studentDtoList.size() > 0){
            result.put("student",studentDtoList);
        }else{
            result = null;
        }

        return JsonResultData.<Map<String,List<StudentDto.Response>>>ApiResultBuilder()
                .data(result)
                .error(null)
                .build();
    }

    @PostMapping("/students")
    public JsonResultData<?> createStudents(@RequestBody StudentDto student){
        Long id = studentService.createStudents(student.getRequest());
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

    @DeleteMapping("/students/{studentId}")
    public JsonResultData<?> deleteStudent(
            @PathVariable("studentId") Long id
    ){
        Boolean isDeleted = studentService.deleteStudent(id);

        if (isDeleted){
            return JsonResultData.ApiResultBuilder()
                    .build();
        }else{
            return JsonResultData.ApiResultBuilder()
                    .build();
        }
    }
}
