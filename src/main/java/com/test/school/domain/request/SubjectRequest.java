package com.test.school.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.school.common.annotation.Name;
import com.test.school.domain.entity.Subject;
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

        @Name(message = "과목 이름은 한글/양어/숫자만을 포함합니다.")
        @Size(min = 1,max = 16 ,message = "과목 이름은 최소 1자 최대 16글자 입니다.")
        String name;

        public Subject toEntity(){
            return Subject.of()
                    .name(this.name)
                    .build();
        }
    }


}
