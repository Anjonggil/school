package com.test.school.domain.entity;

import com.test.school.domain.request.SubjectRequest;
import com.test.school.domain.response.SubjectResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SUBJECT")
public class Subject {
    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_name",length = 50)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Score> scoreList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Subject(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubjectResponse.Info toDto(){
        return SubjectResponse.Info.of()
                .id(this.id)
                .name(this.name)
                .build();
    }

    public double getAverage() {
        double averageScore = -1d;
        if (this.scoreList.size() > 0) averageScore = this.scoreList.stream().mapToDouble(Score::getScore).average().orElse(0.0);
        return averageScore;
    }
}
