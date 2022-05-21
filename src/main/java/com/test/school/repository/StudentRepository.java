package com.test.school.repository;

import com.test.school.domain.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    Student findStudentById(Long id);

    @Query(value = "select s from Student s")
    List<Student> findByAll();

    Student findStudentByPhoneNumber(String phoneNumber);
}
