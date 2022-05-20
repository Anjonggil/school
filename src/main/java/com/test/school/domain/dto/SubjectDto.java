package com.test.school.domain.dto;

import com.test.school.domain.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectDto {
    private Long id;
    private String name;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public SubjectDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject toEntity(){
        return Subject.of()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
