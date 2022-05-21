package com.test.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.domain.Subject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class SubjectDto {

    @JsonProperty("subject")
    private Request request;

    @Getter
    public static class Request{
        String name;

        public Subject toEntity(){
            return Subject.of()
                    .name(this.name)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long id;
        private String name;

        @Builder(builderMethodName = "of",builderClassName = "of")
        public Response(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
