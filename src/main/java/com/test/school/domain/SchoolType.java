package com.test.school.domain;

import lombok.Getter;

@Getter
public enum SchoolType {
    ELEMENTARY("ELEMENTARY"), MIDDLE("MIDDLE"), HIGH("HIGH");
    private final String code;

    SchoolType(String code){
        this.code = code;
    }
}
