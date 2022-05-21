package com.test.school.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.domain.Subject;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectRequest {

    @JsonProperty("subject")
    private Info info;

    @Getter
    public static class Info {
        String name;

        public Subject toEntity(){
            return Subject.of()
                    .name(this.name)
                    .build();
        }
    }


}
