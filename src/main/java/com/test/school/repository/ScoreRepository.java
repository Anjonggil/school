package com.test.school.repository;

import com.test.school.domain.Score;
import com.test.school.domain.Student;
import com.test.school.domain.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score,Long> {
    Score findScoreByStudentAndSubject(Student student, Subject subject);
}
