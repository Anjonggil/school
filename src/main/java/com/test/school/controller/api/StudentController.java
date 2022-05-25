package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.domain.entity.Student;
import com.test.school.domain.request.StudentRequest;
import com.test.school.domain.response.StudentResponse;
import com.test.school.service.StudentSubjectService;
import com.test.school.service.StudentService;
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
public class StudentController {
    private final StudentService studentService;
    private final StudentSubjectService studentSubjectService;

    @Operation(summary = "회원 조회 API", description = "회원 조회 API")
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

    @Operation(summary = "회원 등록 API", description = "회원 등록 API")
    @PostMapping("/students")
    public ResponseEntity<?> createStudents(
            @Parameter(description = "Student Request", required = true)
            @RequestBody @Valid StudentRequest studentRequest
    ){
        Student student = studentService.createStudents(studentRequest.getInfo());
        studentSubjectService.createLectureByStudent(student);

        return new ResponseEntity<>(JsonResultData.ApiResultBuilder()
                .data(null)
                .error(null)
                .build(),HttpStatus.CREATED);
    }

    @Operation(summary = "회원 삭제 API", description = "회원 삭제 API")
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @Parameter(description = "Student Id", required = true)
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
