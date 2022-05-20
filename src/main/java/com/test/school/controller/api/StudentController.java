package com.test.school.controller.api;

import com.test.school.common.JsonResultData;
import com.test.school.common.error.ApiExceptionEntity;
import com.test.school.domain.Student;
import com.test.school.domain.dto.StudentDto;
import com.test.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping("/students")
    public JsonResultData<List<StudentDto>> getStudents(){
        List<StudentDto> studentDtoList = studentService.getStudents();

        return new JsonResultData<>(studentDtoList,null,HttpStatus.OK);
    }

    @PostMapping("/students")
    public JsonResultData<Long> setStudents(
            @RequestBody StudentDto student
    ){
        Long id = studentService.setStudent(student);
        if (id != null){
            return new JsonResultData<>(null,null,HttpStatus.CREATED);
        }else {
            return new JsonResultData<>(null,new ApiExceptionEntity("No Create!","실패"),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable("studentId") Long id
    ){
        Boolean isDelete = studentService.deleteStudent(id);

        if (isDelete){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
