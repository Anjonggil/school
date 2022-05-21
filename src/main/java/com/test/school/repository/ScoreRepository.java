package com.test.school.repository;

import com.test.school.domain.Score;
import com.test.school.domain.Student;
import com.test.school.domain.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score,Long> {
    Score findScoreByStudentAndSubject(Student student, Subject subject);

    @Query(value = "select sc from Score sc join fetch sc.subject where sc.student.id = ?1")
    List<Score> findScoresByStudentId(Long studentId);

    @Query(value = "select sc from Score sc join fetch sc.student where sc.subject.id = ?1")
    List<Score> findScoresBySubjectId(Long subjectId);
}
