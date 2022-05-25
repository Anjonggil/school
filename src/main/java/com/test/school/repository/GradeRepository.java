package com.test.school.repository;

import com.test.school.domain.entity.StudentSubject;
import com.test.school.domain.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    Grade findScoreByStudentSubject(StudentSubject studentSubject);
}
