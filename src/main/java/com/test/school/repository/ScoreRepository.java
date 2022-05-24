package com.test.school.repository;

import com.test.school.domain.entity.Lecture;
import com.test.school.domain.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    Score findScoreByLecture(Lecture lecture);
}
