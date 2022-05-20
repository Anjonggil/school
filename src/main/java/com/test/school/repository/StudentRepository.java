package com.test.school.repository;

import com.test.school.domain.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    Student findStudentById(Long id);

    @Query("select s from Student s")
    List<Student> findByAll();

    Student findStudentByPhoneNumber(String phoneNumber);
}
