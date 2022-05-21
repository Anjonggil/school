package com.test.school.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.common.annotation.Name;
import com.test.school.domain.Subject;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
public class SubjectRequest {

    @Valid
    @JsonProperty("subject")
    private Info info;

    @Getter
    public static class Info {

        @Name
        @Size(min = 1,max = 16)
        String name;

        public Subject toEntity(){
            return Subject.of()
                    .name(this.name)
                    .build();
        }
    }


}
