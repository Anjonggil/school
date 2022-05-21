package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;
import com.test.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<JsonResultData<StudentResponse>> getStudents(){
        StudentResponse studentDtoList = studentService.getStudents();

        return new ResponseEntity<>(
                JsonResultData.<StudentResponse>ApiResultBuilder()
                    .data(studentDtoList)
                    .error(null)
                    .build(),
                HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudents(@RequestBody StudentRequest student){
        Long id = studentService.createStudents(student.getInfo());
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

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable("studentId") Long id
    ){
        Boolean isDeleted = studentService.deleteStudent(id);

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
