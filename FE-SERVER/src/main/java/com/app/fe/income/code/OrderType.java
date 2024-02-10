package com.app.fe.income.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderType {

    ASC("ASC"),
    DESC("DESC");

    private final String value;
}
