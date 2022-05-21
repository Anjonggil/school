package com.test.school.repository;

import com.test.school.domain.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject,Long> {
    Subject findSubjectById(Long id);

    Subject findSubjectByName(String name);

    @Query(value = "select s from Subject s")
    List<Subject> findByAll();
}
