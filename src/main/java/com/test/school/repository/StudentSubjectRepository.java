package com.test.school.repository;

import com.test.school.domain.entity.StudentSubject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends CrudRepository<StudentSubject,Long> {

    @Query(value = "select ss from StudentSubject ss join fetch ss.student st join fetch ss.subject su where st.id = :studentId and  su.id = :subjectId")
    StudentSubject findLectureByStudentIdAndSubjectId(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

//    @Query(value = "select sc from Lecture sc join fetch sc.subject where sc.student.id = ?1")
//    List<Lecture> findScoresByStudentId(Long studentId);
//
//    @Query(value = "select sc from Lecture sc join fetch sc.student where sc.subject.id = ?1")
//    List<Lecture> findScoresBySubjectId(Long subjectId);
}
