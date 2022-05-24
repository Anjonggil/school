package com.test.school.repository;

import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Student;
import com.test.school.domain.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends CrudRepository<Lecture,Long> {

    @Query(value = "select l from Lecture l join fetch l.student st join fetch l.subject su where st.id = :studentId and  su.id = :subjectId")
    Lecture findLectureByStudentIdAndSubjectId(@Param("studentId") Long studentId,@Param("subjectId") Long subjectId);

//    @Query(value = "select sc from Lecture sc join fetch sc.subject where sc.student.id = ?1")
//    List<Lecture> findScoresByStudentId(Long studentId);
//
//    @Query(value = "select sc from Lecture sc join fetch sc.student where sc.subject.id = ?1")
//    List<Lecture> findScoresBySubjectId(Long subjectId);
}
